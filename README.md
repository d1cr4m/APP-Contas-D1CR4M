# App Contas (D1CR4M)

Um aplicativo Android leve, prático e eficiente de controle de finanças pessoais e gestão de contas, desenvolvido para facilitar o dia a dia e manter o orçamento sob controle.

**Versão atual:** 1.3

---

## 📱 Sobre o Aplicativo
O **App Contas** foi pensado para quem precisa de uma ferramenta rápida e sem complicações para registrar ganhos, monitorar despesas pendentes ou pagas, e calcular automaticamente o saldo restante. O app também conta com um sistema prático de lembretes automáticos para evitar atrasos em vencimentos.

Funciona 100% offline, sem anúncios e sem necessidade de conta. Seus dados ficam apenas no seu aparelho.

---

## ✨ Funcionalidades Principais
* **Controle de Receitas e Saldo:** Acompanhe o valor recebido e veja o cálculo automático da "Sobra" em tempo real.
* **Gestão de Despesas:** Adicione novas contas ou compras especificando o valor e a data de vencimento.
* **Marcação rápida:** Caixinha verde com ✓ para marcar a conta como paga com um toque.
* **Lembretes Inteligentes:** Notificações automáticas no dia do pagamento (vence hoje, amanhã e atrasadas), com **horário configurável**.
* **Parcelas:** Cadastre uma compra parcelada e o app gera todas as parcelas com as datas de cada mês.
* **Contas Fixas:** Marque como "repetir todo mês" e, ao pagar, a conta do mês seguinte é criada sozinha.
* **Categorias e Resumo por Mês:** Veja total, pago, a pagar, sobra e gastos por categoria em cada mês.
* **Edição Fácil:** Toque no nome de qualquer conta para editar.
* **Backup:** Exporte e importe seus dados em arquivo `.json`.
* **Filtros Dinâmicos:** Alterne facilmente entre contas **Pendentes**, **Pagas** ou **Todas**.
* **Interface Limpa e Intuitiva:** Opções avançadas escondidas nos menus **⋮** e **⋯**, modo escuro/claro automático e leveza para o sistema Android.

---

## 🛠️ Tecnologias Utilizadas
* **Java / Android Native:** Estrutura nativa do app, notificações e backup de arquivos.
* **WebView (HTML, CSS e JavaScript):** Interface e lógica das contas, empacotadas dentro do próprio APK.
* **AlarmManager / BroadcastReceiver:** Sistema integrado para gerenciamento de alarmes e lembretes de contas, reagendados automaticamente após reiniciar o celular.

---

## 🚀 Como Instalar / Usar
1. Baixe o arquivo APK mais recente na aba de [Releases](../../releases) ou instale diretamente pelo **F-Droid** *(em breve)*.
2. Conceda as permissões necessárias para o recebimento de notificações de vencimento.
3. Comece a gerenciar as suas finanças adicionando a sua renda e contas mensais!

**Requisitos:** Android 5.0 ou superior.

> 💡 Antes de desinstalar o app ou limpar os dados, use **⋮ → Exportar backup** para não perder suas contas.

> 📌 Em celulares Xiaomi, ative o início automático do app e desative a economia de bateria para os lembretes funcionarem.

## 🔒 Segurança

- **Código aberto:** todo o código está neste repositório e pode ser conferido.
- **Sem internet:** o app não pede permissão de internet. Seus dados nunca saem do aparelho.
- **Permissões:** só notificações (lembretes) e reagendar o alarme ao reiniciar o celular.
- **Aviso do Play Protect:** ele aparece porque o app é distribuído fora da Play Store por um desenvolvedor independente. Toque em **Mais detalhes → Instalar mesmo assim**.
- **Conferir o arquivo:** o SHA-256 do `contas.apk` está na descrição da release.
------

## ☕ Apoie o projeto

O App Contas é gratuito, sem anúncios e feito com carinho no meu tempo livre, direto do celular. Ele vai continuar assim, e ninguém precisa pagar nada para usar.

Se o app te ajudou e você quiser contribuir com qualquer valor, ajuda a manter o projeto vivo e a trazer novas melhorias. Se não puder, tudo bem: usar, compartilhar e dar uma ⭐ já me ajuda muito. Obrigado de coração! 💚

**Pix (chave aleatória):**
```
2f56566e-1db5-43b6-af63-4c59eff5ec77
```

**Ou pelo link do Nubank:** [Contribuir via Nubank](https://nubank.com.br/cobrar/2nrkg/6ae1f784-78f5-438a-a734-9202485913be)

<img src="pix-qrcode.png" alt="QR Code Pix" width="220">


Desenvolvido por **D1CR4M**.
