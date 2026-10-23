// Se a página não vem do Spring (porta 8080), aponta direto para a API
const API_URL = window.location.port === '8080' ? '' : 'http://localhost:8080';

function preencher(id, valor) {
  document.getElementById(id).textContent = valor || '-';
}

function mostrarErro(mensagem) {
  const erro = document.getElementById('erro');
  erro.textContent = mensagem;
  erro.hidden = false;
}

async function carregarPerfil() {
  const email = localStorage.getItem('usuarioEmail');

  if (!email) {
    preencher('nome-completo', 'Perfil indisponível');
    mostrarErro('Você precisa fazer login para ver seu perfil.');
    return;
  }

  try {
    const resposta = await fetch(`${API_URL}/users/perfil?email=${encodeURIComponent(email)}`);

    if (!resposta.ok) {
      throw new Error('Não foi possível carregar o perfil.');
    }

    const dados = await resposta.json();

    const nomeCompleto = `${dados.nome ?? ''} ${dados.sobrenome ?? ''}`.trim();
    preencher('nome-completo', nomeCompleto);
    preencher('email', dados.email);

    ['nome', 'sobrenome', 'telefone', 'cep', 'rua', 'numero',
     'bairro', 'cidade', 'estado', 'observacao']
      .forEach(campo => preencher(campo, dados[campo]));
  } catch (e) {
    preencher('nome-completo', 'Erro ao carregar');
    mostrarErro(e.message === 'Failed to fetch'
      ? 'Não foi possível conectar ao servidor.'
      : e.message);
  }
}

carregarPerfil();