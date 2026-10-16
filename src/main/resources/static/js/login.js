const form = document.getElementById('loginForm');
const mensagem = document.getElementById('mensagem');

form.addEventListener('submit', async (event) => {
    event.preventDefault(); // impede o envio padrão do formulário

    // Monta o objeto {email: "...", senha: "..."} a partir dos campos (usa o atributo name)
    const dados = Object.fromEntries(new FormData(form));

    try {
        const resposta = await fetch('http://localhost:8080/users/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dados)
        });

        // A API devolve texto puro ("Login efetuado..." ou "Email ou senha errados")
        const texto = await resposta.text();

        if (resposta.ok) {
            mostrarMensagem(texto, true);
            window.location.href = 'home.html'; // descomente para redirecionar após o login
        } else {
            mostrarMensagem(texto, false);
            form.senha.value = ''; // limpa a senha digitada
        }
    } catch (erro) {
        mostrarMensagem('Não foi possível conectar ao servidor.', false);
    }
});

function mostrarMensagem(texto, sucesso) {
    mensagem.textContent = texto;               // textContent evita injeção de HTML
}