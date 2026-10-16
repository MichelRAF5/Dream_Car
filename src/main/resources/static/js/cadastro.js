const form = document.getElementById('loginForm');
const mensagem = document.getElementById('mensagem');
const botao = document.getElementById('botao-login');

// Se a página não vem do Spring (porta 8080), aponta direto para a API
const API = window.location.port === '8080' ? '' : 'http://localhost:8080';

form.addEventListener('submit', async (event) => {
    event.preventDefault();
    mensagem.textContent = '';

    const { email, senha, confirmarSenha } = Object.fromEntries(new FormData(form));

    // 1. Confere as senhas ANTES de chamar a API
    if (senha !== confirmarSenha) {
        mensagem.textContent = 'As senhas não coincidem.';
        form.confirmarSenha.value = '';
        form.confirmarSenha.focus();
        return;
    }

    botao.disabled = true; // evita cliques repetidos enquanto espera a resposta

    try {
        // 2. Manda só email e senha (a confirmação não vai para a API)
        const resposta = await fetch(`${API}/users/cadastrar`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email, senha })
        });

        if (resposta.status === 201 || resposta.status === 409) {
            // 3. Mostra a mensagem que a API devolveu
            mensagem.textContent = await resposta.text();

            if (resposta.status === 201) {
                // sucesso: dá 1,5s para ler a mensagem e vai para o login
                setTimeout(() => { window.location.href = 'login.html'; }, 1500);
                return;
            }
        } else {
            mensagem.textContent = `Erro inesperado (${resposta.status}). Tente novamente.`;
        }
    } catch (erro) {
        mensagem.textContent = 'Não foi possível conectar ao servidor.';
    }

    botao.disabled = false;
});