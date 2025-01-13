import com.fastcgi.FCGIInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class FastCGIHandler {
    private final float[] xValues = {-5, -4, -3, -2, -1, 0, 1, 2, 3};
    private final FCGIInterface fcgi;
    ObjectMapper objectMapper;

    public FastCGIHandler(FCGIInterface fcgi) {
        this.fcgi = fcgi;
        objectMapper = new ObjectMapper();
    }

    public void handleRequest() throws IOException {
        long startTime = System.currentTimeMillis();
        Main.logger.info("Request sleep");
        Request request;
        try {
            request = readRequestBody();
        } catch (Exception e) {
            sendError("В теле запроса должны быть переданы целочисленные значения X, Y и R (-5 <= X <= 3; -3 <= Y <= 5; 2 <= R <= 5).");
            return;
        }
        Main.logger.info("Request received: " + request);
        if (validateRequest(request)) {
            sendResponse(request.x(),
                    request.y(),
                    request.r(),
                    checkHit(request),
                    System.currentTimeMillis() - startTime);
        } else {
            sendError("В теле запроса должны быть переданы целочисленные значения X, Y и R (-5 <= X <= 3; -3 <= Y <= 5; 2 <= R <= 5).");
        }
    }

    public boolean validateRequest(Request request) {
        if (request == null) {
            return false;
        }
        if (request.r() < 2f || request.x() > 5f) {
            return false;
        }
        if (request.y() < -3f || request.y() > 5f) {
            return false;
        }
        for (float val : xValues) {
            if (val == request.x()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkHit(Request request) {
        float x = request.x();
        float y = request.y();
        float r = request.r();
        if (x < 0 && y < 0) {
            return false;
        }
        if (x > 0 && y < 0) {
            if(x < r / 2) {
                return x - (r / 2) < y;
            }
            return false;
        }
        if (x <= 0 && y >= 0) {
            return y <= (r / 2f) && x >= -r;
        }
        if (x >= 0 && y >= 0) {
            if (x >= (r / 2) || y >= (r / 2)) return false;
            return (x * x + y * y) <= ((r / 2) * (r / 2));
        }
        return false;
    }

    private Request readRequestBody() throws IOException {
        fcgi.request.inStream.fill();
        var contentLength = fcgi.request.inStream.available();
        var buffer = ByteBuffer.allocate(contentLength);
        var readBytes = fcgi.request.inStream.read(buffer.array(), 0, contentLength);
        var requestBodyRaw = new byte[readBytes];
        buffer.get(requestBodyRaw);
        buffer.clear();
        return objectMapper.readValue(new String(requestBodyRaw, StandardCharsets.UTF_8), Request.class);
    }

    public void sendResponse(float x, float y, float r, boolean result, long processingTime) throws JsonProcessingException {
        String body = objectMapper.writeValueAsString(new Response(x, y, r, result, processingTime));
        String httpResponse = """
                HTTP/1.1 200 OK
                Content-Type: text/html
                Content-Length: %d
                Access-Control-Allow-Origin: *
                
                %s
                """.formatted(body.getBytes().length, body);
        Main.logger.info("send" + httpResponse);
        System.out.println(httpResponse);
    }

    public void sendError(String message) {
        String httpResponse = """
                HTTP/1.1 400 Bad Request
                Content-Type: text
                Content-Length: %d
                Access-Control-Allow-Origin: *
                
                %s
                """.formatted(message.getBytes().length, message);
        Main.logger.info("send" + httpResponse);
        System.out.println(httpResponse);
    }
}