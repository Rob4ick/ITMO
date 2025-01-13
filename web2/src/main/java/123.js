let checkboxes = document.querySelectorAll('.checkbox input[type="checkbox"]');

counter = 0;

document.querySelectorAll('.checkbox').forEach((checkbox) => {
    checkbox.addEventListener('change', function() {
        document.querySelectorAll('.checkbox').forEach((cb) => {
            if (cb !== this) {
                cb.checked = false;
            }
        });
    });
});

document.getElementById("submit-button").addEventListener("click", function() {
    errorValid = 1;
    const isChecked = Array.from(document.querySelectorAll('.checkbox')).some(cb => cb.checked)
    const error_x = document.getElementById("errorX");
    error_x.textContent = '';
    error_x.classList.remove('error-visible');
    if (!isChecked) {
        error_x.textContent = "Ошибка: выберите X.";
        error_x.style.color = "red";
        error_x.classList.add('error-visible');
        errorValid -= 1;
    }

    const value_y = document.getElementById("y-coordinate").value;
    const error_y = document.getElementById("errorY");
    const input_y = document.getElementById("y-coordinate");

    error_y.textContent = "";
    error_y.classList.remove('error-visible');
    error_y.style.color = "red";
    input_y.style.border = '1px solid grey';
    input_y.style.borderRadius = "5px";
    input_y.style.outlineColor = "black";

    if (isNaN(value_y) || value_y === "") {
        error_y.textContent = "Ошибка: введите число.";
        input_y.style.border = '2px solid red';
        input_y.style.outlineColor = "red"
        input_y.style.borderRadius = "5px";
        error_y.classList.add('error-visible');
    } else {
        const numberValue = parseFloat(value_y);
        if (numberValue >= -3 && numberValue <= 5) {
            error_y.textContent = "Число корректное!";
            error_y.style.color = "green";
            errorValid += 1;
        } else {
            error_y.textContent = "Ошибка: число должно быть в диапазоне от -3 до 5.";
            input_y.style.border = '2px solid red';
            input_y.style.outlineColor = "red";
            input_y.style.borderRadius = "5px";
            error_y.classList.add('error-visible');
        }
    }

    const value_r = document.getElementById("rValue").value;
    const error_r = document.getElementById("errorR");
    const input_r = document.getElementById("rValue");
    error_r.textContent = "";
    error_r.classList.remove('error-visible');
    error_r.style.color = "red";
    input_r.style.border = '1px solid grey';
    input_r.style.borderRadius = "5px";
    input_r.style.outlineColor = "black";

    if (isNaN(value_r) || value_r === "") {
        error_r.textContent = "Ошибка: введите число.";
        input_r.style.border = '2px solid red';
        input_r.style.outlineColor = "red";
        input_r.style.borderRadius = "5px";
        error_r.classList.add('error-visible');
    } else {
        const numberValue = parseFloat(value_r);
        if (numberValue >= 2 && numberValue <= 5) {
            error_r.textContent = "Число корректное!";
            error_r.style.color = "green";
            errorValid += 1;
        } else {
            error_r.textContent = "Ошибка: число должно быть в диапазоне от 2 до 5.";
            input_r.style.border = '2px solid red';
            input_r.style.outlineColor = "red";
            input_r.style.borderRadius = "5px";
            error_r.classList.add('error-visible');
        }
    }

    if (errorValid === 3) {
        handleSubmitForm(value_y, value_r);
    }
});

document.getElementById("clearTableButton").addEventListener("click", function() {
    const tableBody = document.getElementById("results-body");
    counter = 0;
    tableBody.innerHTML = '';
});

function addResult(x, y, r, result, processingTime) {
    const tbody = document.getElementById('results-body');
    const currentTime = new Date().toLocaleTimeString();

    let row = `<tr>
            <td>${++counter}</td>
            <td>${x}</td>
            <td>${y}</td>
            <td>${r}</td>
            <td>${result ? 'Попал' : 'Не попал'}</td>
            <td>${currentTime}</td>
            <td>${processingTime} ms</td>
        </tr>`;
    tbody.innerHTML += row;
    tbody.rows[counter - 1].classList.add(result ? 'hit' : 'miss');
}

function getXValue(){
    const selectedCheckbox = document.querySelector('.checkbox:checked');
    // let result = null;
    // checkboxes.forEach(cb => {
    //     if(cb.checked){
    //         result = cb.value;
    //     }
    // });
    if (selectedCheckbox)
        return selectedCheckbox.value;
    return null;
}

function handleSubmitForm(y, r) {
    let x = getXValue();
    let request = {
        x: x,
        y: y,
        r: r
    };
    fetch('http://localhost:8080/fcgi-bin/WebLab1-1.0-SNAPSHOT.jar', {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(request)
    })
        .then(response => {
            if(!response.ok)
                throw new Error('HTTP error!');
            return response.json();
        })
        .then(response => {
            addResult(response.x, response.y, response.r,  response.result, response.processingTime);
        })
}
