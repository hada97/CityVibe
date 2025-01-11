let userId = null;
const baseUrl = "http://localhost:8080";
const apiUrlEventos = `${baseUrl}/eventos`;
const token = localStorage.getItem("authToken");


// Função para verificar se o usuário está logado
function verificarLogin() {
    const token = localStorage.getItem("authToken");  // Verifica se o token de autenticação existe
    const btnPublicarEvento = document.querySelector(".btn");

    // Se o token existir, redireciona para a página de publicação do evento
    if (token) {
        btnPublicarEvento.href = "/publicar-evento";  // Altere para a URL correta da página de publicar evento
        btnPublicarEvento.innerText = "Publicar Evento";  // Pode alterar o texto do botão
    } else {
        // Se o token não existir, direciona para a página de login
        btnPublicarEvento.href = "/login";
        btnPublicarEvento.innerText = "Faça Login para Publicar";
    }
}

// Chama a função ao carregar a página
window.onload = verificarLogin;
