let userId = null;
const baseUrl = "http://localhost:8080";
const apiUrlEventos = `${baseUrl}/eventos`;
const token = localStorage.getItem("authToken");


function logout() {
  fetch(`${baseUrl}/users/logout`, {
    method: 'POST',
    headers: {
      'Authorization': `Bearer ${token}`,
    },
  })
  .then(response => {
    if (response.ok) {
      alert("Você saiu!");
      window.location.href = "/";
    } else {
      alert("Erro ao tentar sair.");
    }
  })
  .catch(error => {
    console.error("Erro na comunicação com o servidor:", error);
    alert("Erro ao tentar sair.");
  });
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

    // Verifica se o userId foi carregado corretamente
    if (!userId) {
      alert("Erro: o userId não foi carregado.");
      return;
    }

    // Coleta os dados do formulário
    const name = document.querySelector('input[placeholder="Nome do evento"]').value;
    const eventType = document.querySelector('select').value;
    const city = document.querySelector('input[placeholder="Cidade"]').value;
    const address = document.querySelector('input[placeholder="Endereço"]').value;
    const link = document.querySelector('input[placeholder="Link (opcional)"]').value;
    const date = document.querySelector('input[type="date"]').value;
    const time = document.querySelector('input[type="time"]').value;
    const cost = document.querySelector('input[name="evento"]:checked').value;
    const description = document.querySelector('textarea[placeholder="Descrição do evento"]').value;
    const eventCover = document.querySelector('#event-cover').value;

    const fullDateTime = `${date}T${time}:00`;

    // Criar o objeto de dados a serem enviados para a API
    const eventData = {
      nome: name,
      tipoEvento: eventType,
      cidade: city,
      endereco: address,
      link: link,
      data: date,
      hora: fullDateTime,
      custo: cost,
      descricao: description,
      capa: eventCover,
      userId: userId
    };

    // Tentando enviar para a API
    try {
      const response = await fetch(apiUrlEventos, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(eventData),
      });

      if (response.ok) {
        alert("Evento criado com sucesso!");
        document.querySelector(".event-form").reset();
      } else {
        const data = await response.json();
        if (data.message) {
          alert("Erro: " + data.message);
        } else {
          alert("Erro desconhecido.");
        }
      }
    } catch (error) {
      alert("Erro ao tentar criar o evento: " + error.message);
    }
  });


// Inicialização ao carregar o DOM
document.addEventListener("DOMContentLoaded", async () => {
  await fetchUserId();
});
