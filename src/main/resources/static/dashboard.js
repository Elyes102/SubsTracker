const token = localStorage.getItem("token");
const subscriptionsList = document.getElementById("subscriptions-list");
const errorMessage = document.getElementById("error-message");
const nomInput = document.getElementById("nom");
const coutInput = document.getElementById("cout");
const addBtn = document.getElementById("addBtn");


if (!token) {
    window.location.href = "/login.html";
}


function displaySubscriptions(subscriptions) {
    subscriptionsList.innerHTML = "";
    subscriptions.forEach(sub => {
        const li = document.createElement("li");
        const span = document.createElement("span");

        span.textContent = `${sub.name} - ${sub.pricePerMonth} €`;
        li.appendChild(span);

        const delBtn = document.createElement("button");
        delBtn.textContent = "Supprimer";
        delBtn.onclick = () => deleteSubscription(sub.id);
        li.appendChild(delBtn);

        subscriptionsList.appendChild(li);
    });
}


function loadSubscriptions() {
    fetch("/api/subscriptions", {
        headers: { Authorization: `Bearer ${token}` }
    })
        .then(res => {
            if (!res.ok) throw new Error("Impossible de charger les abonnements");
            return res.json();
        })
        .then(data => {
            displaySubscriptions(data);
            errorMessage.textContent = "";
        })
        .catch(err => {
            errorMessage.textContent = err.message;
            if (err.message.includes("401")) {
                localStorage.removeItem("token");
                window.location.href = "/login.html";
            }
        });
}


function addSubscription() {
    const nom = nomInput.value.trim();
    const cout = parseFloat(coutInput.value);

    if (!nom || isNaN(cout) || cout < 0) {
        errorMessage.textContent = "Merci de saisir un nom valide et un coût positif.";
        return;
    }

    fetch("/api/subscriptions", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`
        },
        body: JSON.stringify({
            name: nom,
            pricePerMonth: cout,
            frequency: "monthly",
            startDate: "2025-07-16",
            nextPaymentDate: "2025-08-16"
        })

    })
        .then(res => {
            if (!res.ok) throw new Error("Erreur lors de l’ajout de l’abonnement");
            return res.json();
        })
        .then(newSubscription => {
            loadSubscriptions();
            nomInput.value = "";
            coutInput.value = "";
            errorMessage.textContent = "";
        })
        .catch(err => {
            errorMessage.textContent = err.message;
        });
}


function deleteSubscription(id) {
    fetch(`/api/subscriptions/${id}`, {
        method: "DELETE",
        headers: {
            Authorization: `Bearer ${token}`
        }
    })
        .then(res => {
            if (!res.ok) throw new Error("Erreur lors de la suppression");
            loadSubscriptions();
        })
        .catch(err => {
            errorMessage.textContent = err.message;
        });
}


addBtn.addEventListener("click", addSubscription);


document.getElementById("logoutBtn").addEventListener("click", () => {
    localStorage.removeItem("token");
    window.location.href = "/login.html";
});


loadSubscriptions();
