let userId = null;
const baseUrl = "http://localhost:8080";
const apiUrlEventos = `${baseUrl}/eventos`;
const token = localStorage.getItem("authToken");

// Logout
function logout() {
  alert("Você saiu!");
  window.location.href = "/";
}

// Busca o userId na inicialização
const fetchUserId = async () => {
  try {
    const response = await fetch(`${baseUrl}/users/login/id`, {
      headers: { Authorization: `Bearer ${token}` },
    });

    if (!response.ok) throw new Error("Falha ao obter o userId");

    userId = await response.json();
    console.log("User ID:", userId);
  } catch (error) {
    console.error("Erro ao buscar userId:", error);
  }
};

// Formulário para registrar um novo evento
document
  .querySelector(".event-form")
  .addEventListener("submit", async function (event) {
    event.preventDefault();

    // Coleta os dados do formulário
    const name = document.querySelector('input[placeholder="Nome do evento"]').value;
    const eventType = document.querySelector('select').value;
    const city = document.querySelector('input[placeholder="Cidade"]').value;
    const address = document.querySelector('input[placeholder="Endereço"]').value;
    const link = document.querySelector('input[placeholder="Link (opcional)"]').value;
    const date = document.querySelector('input[type="date"]').value;
    const time = document.querySelector('input[type="time"]').value;
    const cost = document.querySelector('input[name="event-cost"]:checked').value;
    const description = document.querySelector('textarea[placeholder="Descrição do evento"]').value;

    // Tentando enviar para a API
    try {
      const response = await fetch(apiUrlEventos, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({
          name,
          eventType,
          city,
          address,
          link,
          date,
          time,
          cost,
          description
        }),
      });

      if (response.ok) {
        alert("Evento criado com sucesso!");
        document.querySelector(".event-form").reset();
      } else {
        const data = await response.json();
        alert("Erro: " + data.message);
      }
    } catch (error) {
      alert("Erro ao tentar criar o evento: " + error.message);
    }
  });

// Carregar o nome do usuário após login
fetch("http://localhost:8080/api/user/profile", {
  headers: {
    Authorization: `Bearer ${token}`,
  },
})
  .then((response) => response.json())
  .then((data) => {
    document.getElementById("user-name").innerText = data.name;
  })
  .catch((error) => {
    console.error("Erro ao carregar o nome do usuário:", error);
    document.getElementById("user-name").innerText = "Erro ao carregar o nome";
  });

// Inicialização ao carregar o DOM
document.addEventListener("DOMContentLoaded", async () => {
  const [userIdResult] = await Promise.all([fetchUserId()]);
});
