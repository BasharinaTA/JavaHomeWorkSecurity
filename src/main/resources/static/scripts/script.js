"use strict"

document.querySelectorAll("button")
    .forEach(button =>
        button.addEventListener("click", () => {
            let form = document.forms.form;
            form.id.value = "";
            form.name.value = "";
            form.composition.value = "";
            form.classList.remove("hidden");

            let tr = button.closest("tr");
            if (!tr) return;
            let td = tr.querySelectorAll("td");
            let arrValues = [];
            for (let i = 0; i < td.length; i++) {
                arrValues[i] = td[i].innerText;
            }
            form.id.value = arrValues[0];
            form.name.value = arrValues[1];
            form.composition.value = arrValues[2];
        }));