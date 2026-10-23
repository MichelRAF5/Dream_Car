const formLogin = document.getElementById('loginForm');
const mensagemLogin = document.getElementById('mensagem');
const botaoLogin = document.getElementById('botao-login');

// Se a página não vem do Spring (porta 8080), aponta direto para a API
const API_URL = window.location.port === '8080' ? '' : 'http://localhost:8080';

formLogin.addEventListener('submit', async (event) => {
    event.preventDefault(); // Impede a atualização padrão da página
    mensagemLogin.textContent = '';
    botaoLogin.disabled = true;

    // Extrai email e senha usando o atributo name dos inputs
    const dadosLogin = Object.fromEntries(new FormData(formLogin));

    try {
        const resposta = await fetch(`${API_URL}/users/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dadosLogin)
        });

        const texto = await resposta.text();

        if (resposta.ok) {
            mensagemLogin.style.color = '#4CAF50';
            mensagemLogin.textContent = texto;
            
            // Redireciona para a home após o login bem-sucedido
            setTimeout(() => {
                localStorage.setItem('usuarioEmail', dadosLogin.email); // Armazena o email do user logado atualmente no armaenamento local (usado para a exibição posterior dos dados do cliente)
                window.location.href = 'home.html';
            }, 1000);
        } else {
            mensagemLogin.style.color = 'red';
            mensagemLogin.textContent = texto;
            if (formLogin.senha) formLogin.senha.value = ''; // Limpa o campo de senha
            botaoLogin.disabled = false;
        }
    } catch (erro) {
        mensagemLogin.style.color = 'red';
        mensagemLogin.textContent = 'Não foi possível conectar ao servidor.';
        botaoLogin.disabled = false;
    }
});