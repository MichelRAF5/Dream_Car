document.addEventListener('DOMContentLoaded', async () => {
    const container = document.getElementById('header-container');
    if (!container) return;

    try {
        const resposta = await fetch('header.html');
        if (resposta.ok) {
            container.innerHTML = await resposta.text();
        } else {
            console.error('Erro ao carregar o header:', resposta.status);
        }
    } catch (erro) {
        console.error('Falha na requisição do header:', erro);
    }
});