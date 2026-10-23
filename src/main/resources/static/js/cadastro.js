const formCadastro = document.getElementById('cadastroForm');
const mensagemCadastro = document.getElementById('mensagem');
const botaoCadastrar = document.getElementById('botao-cadastrar');

const API_URL = window.location.port === '8080' ? '' : 'http://localhost:8080';

formCadastro.addEventListener('submit', async (event) => {
    event.preventDefault();
    mensagemCadastro.textContent = '';

    // Extrai todos os campos usando o atributo 'name' dos inputs
    const dados = Object.fromEntries(new FormData(formCadastro));

    // 1. Confere se as senhas são iguais antes de enviar
    if (dados.senha !== dados.confirmarSenha) {
        mensagemCadastro.style.color = 'red';
        mensagemCadastro.textContent = 'As senhas não coincidem.';
        if (formCadastro.confirmarSenha) {
            formCadastro.confirmarSenha.value = '';
            formCadastro.confirmarSenha.focus();
        }
        return;
    }

    botaoCadastrar.disabled = true;

    // 2. Remove o campo 'confirmarSenha' para enviar o JSON exato do CadastroDTO
    delete dados.confirmarSenha;

    try {
        // 3. Envia o DTO completo com dados de acesso, pessoa e endereço
        const resposta = await fetch(`${API_URL}/users/cadastrar`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dados)
        });

        const texto = await resposta.text();

        if (resposta.status === 201) { // HttpStatus.CREATED
            mensagemCadastro.style.color = '#4CAF50';
            mensagemCadastro.textContent = texto;

            // Sucesso: Redireciona para a tela de login após 1.5s
            setTimeout(() => {
                window.location.href = 'login.html';
            }, 1500);
        } else {
            mensagemCadastro.style.color = 'red';
            mensagemCadastro.textContent = texto;
            botaoCadastrar.disabled = false;
        }
    } catch (erro) {
        mensagemCadastro.style.color = 'red';
        mensagemCadastro.textContent = 'Não foi possível conectar ao servidor.';
        botaoCadastrar.disabled = false;
    }
});