let userId = null;
const baseUrl = "https://city-vibe-cjc3gae3fphaa9gu.canadacentral-01.azurewebsites.net/";
const token = localStorage.getItem("authToken");


// Função para obter o ID do usuário
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

// Função para carregar os eventos do usuário
async function carregarMeusEventos() {
  if (!userId) {
    console.error("ID do usuário não encontrado");
    return;
  }
  try {
    const url = `${baseUrl}/users/eventos/${userId}?page=0&size=5`;

    const response = await fetch(url, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${token}`,
      },
    });

    if (!response.ok) {
      throw new Error("Falha ao carregar os eventos");
    }

    const data = await response.json();
    const eventos = data.content;
    console.log("Eventos carregados:", eventos);

    if (!Array.isArray(eventos)) {
      console.error("A resposta não contém um array de eventos.");
      return;
    }

    const container = document.getElementById("event-cards-container");
    container.innerHTML = "";

    eventos.forEach((evento) => {
      const card = document.createElement("div");
      card.classList.add("card");

      card.innerHTML = `
            <img src="${evento.capa}" alt="${evento.nome}" class="card-image">
            <div class="div-content">
                <div class="card-content">
                    <h3 class="card-title">${evento.nome}</h3>
                    <p class="card-description">${evento.descricao}</p>
                    <p class="card-date">
                        ${new Date(evento.data).toLocaleDateString()}
                        às
                        ${new Date(`${evento.data}T${evento.hora}`).toLocaleTimeString([], {
                          hour: "2-digit",
                          minute: "2-digit",
                        })}
                    </p>
                </div>
                <span class="card-city">${evento.cidade}</span> <!-- Adicionando o nome da cidade -->
            </div>
        `;

      container.appendChild(card);
    });
  } catch (error) {
    console.error("Erro ao carregar eventos:", error);
    alert("Erro ao carregar eventos.");
  }
}


// Função para fazer o logout
function logout() {
  alert("Você saiu!");
  localStorage.removeItem("authToken"); // Remove o token de autenticação
  window.location.href = "/"; // Redireciona para a página de login
}

// Inicialização ao carregar o DOM
document.addEventListener("DOMContentLoaded", async () => {
  await fetchUserId();
  carregarMeusEventos()
});
