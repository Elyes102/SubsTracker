const token = localStorage.getItem("token");
const subscriptionsList = document.getElementById("subscriptions-list");
const errorMessage = document.getElementById("error-message");
const nomInput = document.getElementById("nom");
const coutInput = document.getElementById("cout");
const startDateInput = document.getElementById("startDate");
const addBtn = document.getElementById("addBtn");

if (!token) {
    window.location.href = "/login.html";
}

function daysBetween(date1, date2) {
    const diffTime = date2.getTime() - date1.getTime();
    return Math.ceil(diffTime / (1000 * 60 * 60 * 24));
}

function displaySubscriptions(subscriptions) {
    subscriptionsList.innerHTML = "";
    subscriptions.forEach(sub => {
        const li = document.createElement("li");


        const today = new Date();
        const nextPaymentDate = new Date(sub.nextPaymentDate);
        const daysRemaining = daysBetween(today, nextPaymentDate);

        li.innerHTML = `
      <strong>${sub.name}</strong> - ${sub.pricePerMonth.toFixed(2)} € / mois<br/>
      Start: ${new Date(sub.startDate).toLocaleDateString()}<br/>
      Next payment: ${nextPaymentDate.toLocaleDateString()}<br/>
      Days remaing: ${daysRemaining >= 0 ? daysRemaining : 0}
      <button id="deleteButton" style="margin-left: 10px;">Delete</button>
    `;


        li.querySelector("button").onclick = () => deleteSubscription(sub.id);

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
    const startDate = startDateInput.value;

    if (!nom || isNaN(cout) || cout < 0 || !startDate) {
        errorMessage.textContent = "Not valid";
        return;
    }


    const start = new Date(startDate);
    const nextPaymentDate = new Date(start);
    nextPaymentDate.setMonth(nextPaymentDate.getMonth() + 1);

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
            startDate: startDate,
            nextPaymentDate: nextPaymentDate.toISOString().slice(0,10)
        })
    })
        .then(res => {
            if (!res.ok) throw new Error("Could not add subscription");
            return res.json();
        })
        .then(newSubscription => {
            loadSubscriptions();
            nomInput.value = "";
            coutInput.value = "";
            startDateInput.value = "";
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
            if (!res.ok) throw new Error("Could not delete subscription");
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
