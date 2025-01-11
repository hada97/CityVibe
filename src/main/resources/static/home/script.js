let userId = null;

// Variáveis globais
const baseUrl = "http://localhost:8080";
const apiUrlEventos = `${baseUrl}/eventos`;
const token = localStorage.getItem("authToken"); // Obtém o token de autenticação do localStorage

// Função para verificar se o usuário está logado
function verificarLogin() {
  if (!token) {
    alert("Você precisa estar logado para visualizar os eventos.");
    window.location.href = "/login"; // Redireciona para a página de login se o token não existir
  }
}

// Função para carregar os eventos da API
async function carregarEventos() {
  try {
    // Requisição GET para obter os eventos (sem o cabeçalho Authorization)
    const response = await fetch(apiUrlEventos, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });

    // Verifica se a resposta da API foi bem-sucedida
    if (!response.ok) {
      throw new Error("Falha ao carregar os eventos");
    }

    // Converte a resposta para JSON
    const eventos = await response.json();

    // Seleciona o contêiner onde os eventos serão exibidos
    const container = document.getElementById("event-cards-container");
    container.innerHTML = ""; // Limpa o conteúdo anterior

    // Cria os cards para cada evento
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
                        ${new Date(
                          `${evento.data}T${evento.hora}`
                        ).toLocaleTimeString([], {
                          hour: "2-digit",
                          minute: "2-digit",
                        })}
                    </p>
                </div>
                <span class="card-city">${
                  evento.cidade
                }</span> <!-- Adicionando o nome da cidade -->
            </div>
        `;

      container.appendChild(card); // Adiciona o card ao contêiner
    });
  } catch (error) {
    console.error("Erro ao carregar eventos:", error);
    alert("Erro ao carregar eventos.");
  }
}

// Inicializa o processo quando a página for carregada
document.addEventListener("DOMContentLoaded", () => {
  carregarEventos(); // Carrega os eventos
});

// Função para fazer o logout
function logout() {
  alert("Você saiu!");
  localStorage.removeItem("authToken"); // Remove o token de autenticação
  window.location.href = "/login"; // Redireciona para a página de login
}

// Inicializa o processo quando a página for carregada
document.addEventListener("DOMContentLoaded", () => {
  carregarEventos(); // Carrega os eventos
});


// script.js
document.addEventListener("DOMContentLoaded", () => {
    const loginButton = document.querySelector(".btn");

    // Obtém o token de autenticação armazenado no localStorage
    const token = localStorage.getItem("authToken");

    console.log("Token obtido:", token); // Log para depuração

    // Se o token existir, o usuário está logado
    if (token) {
        console.log("Usuário logado. Redirecionando para index...");
        loginButton.href = "/index"; // Redireciona para a página index
    } else {
        console.log("Usuário não logado. Redirecionando para login...");
        loginButton.href = "/login"; // Redireciona para a página de login
    }
});


function fazerLogin() {
    const token = "seu_token_de_autenticacao"; // Este token seria fornecido pelo servidor após login bem-sucedido
    localStorage.setItem("authToken", token); // Armazena o token no localStorage

    // Redireciona para a página index
    window.location.href = "/index"; // Aqui seria a sua página de eventos
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