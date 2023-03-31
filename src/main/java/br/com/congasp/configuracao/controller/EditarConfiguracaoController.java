package br.com.congasp.configuracao.controller;

public class EditarConfiguracaoController {

	
//	 private void frmConfiguracao_Load(object sender, EventArgs e)
//     {
//         this.CarregaComboMes();
//         this.CarregaComboExercicio();
//         this.CarregaConfiguracaoExercicioValida();
//         this.CarregaComboBancos();
//         this.CarregaConfEmail();
//         txtCompetencia.Enabled = false;
//         txtExercicio.Enabled = false;
//         txtStatus.Enabled = false;
//         txtDefaultAtual.Enabled = false;
//     }
//
//     private void CarregaComboMes()
//     {
//         Dictionary<string, int> test = new Dictionary<string, int>();
//         test.Add("Janeiro", 1);
//         test.Add("Fevereiro", 2);
//         test.Add("Março", 3);
//         test.Add("Abril", 4);
//         test.Add("Maio", 5);
//         test.Add("Junho", 6);
//         test.Add("Julho", 7);
//         test.Add("Agosto", 8);
//         test.Add("Setembro", 9);
//         test.Add("Outubro", 10);
//         test.Add("Novembro", 11);
//         test.Add("Dezembro", 12);
//
//         cmbCompetencia.DataSource = new BindingSource(test, null);
//         cmbCompetencia.DisplayMember = "Key";
//         cmbCompetencia.ValueMember = "Value";
//     }
//
//     private void CarregaComboExercicio()
//     {
//         Dictionary<int, int> test = new Dictionary<int, int>();
//         int ano = DateTime.Now.Year;
//         int indice = 0;
//         test.Add(ano, ano);
//         test.Add(ano - 1, ano - 1);
//         test.Add(ano - 2, ano - 2);
//         test.Add(ano + 1 , ano + 1);
//         test.Add(ano + 2, ano + 2);
//         test.OrderBy(it => it.Value);
//         cmbExercicio.DataSource = new BindingSource(test, null);
//         cmbExercicio.DisplayMember = "Key";
//         cmbExercicio.ValueMember = "Value";
//
//         Configuracao confExercicio = new Configuracao();
//         Configuracao confBD = new Configuracao();
//         confExercicio = confBD.GetLastValidConfExercicio();
//
//         cmbExercicio.SelectedValue = int.Parse(confExercicio.Exercicio);
//         
//     }
//
//     private void CarregaComboBancos()
//     {
//         Dictionary<string, string> test = new Dictionary<string, string>();
//         Bancos bancoBD = new Bancos();
//         List<Bancos> bancos = bancoBD.Listar();
//
//         foreach (Bancos banco in bancos)
//         {
//             test.Add(banco.NomeBanco, banco.CodBanco);
//         }
//         cmbBancos.DataSource = new BindingSource(test, null);
//         cmbBancos.DisplayMember = "Key";
//         cmbBancos.ValueMember = "Value";
//     }
//
//     private void CarregaConfiguracaoExercicioValida()
//     {
//         int testeCompetencia = 0;
//         Configuracao confExercicio = new Configuracao();
//         Configuracao confConta = new Configuracao();
//         Configuracao confBD = new Configuracao();
//         confExercicio = confBD.GetLastValidConfExercicio();
//         confConta = confBD.GetContaDefault();
//
//         txtExercicio.Text = confExercicio.Exercicio;
//         txtCompetencia.Text = confExercicio.Mes;
//         txtDefaultAtual.Text = confConta.ContaDefault;
//
//         if (confExercicio.Ativo == "X")
//         {
//             chkStatus.Checked = true;
//         }
//
//         if (confExercicio.Ativo == " ")
//         {
//             txtStatus.Text = "Inativo";
//         }
//         else
//         {
//             txtStatus.Text = "Ativo";
//         }
//
//         testeCompetencia = Convert.ToInt16(confExercicio.Mes);
//
//         if (testeCompetencia == 12)
//         {
//             testeCompetencia = 12 - 1;
//         }
//         cmbCompetencia.SelectedIndex = testeCompetencia -1;
//         if (cmbCompetencia.SelectedIndex == 1)
//         {
//             cmbCompetencia.SelectedIndex = 0;
//         }
//         else if(testeCompetencia != 11)
//         {
//           cmbCompetencia.SelectedIndex = cmbCompetencia.SelectedIndex - 1;
//         }
//
//         if (testeCompetencia == 1)
//         {
//             cmbCompetencia.SelectedIndex = 0;
//         }
//     }
//
//     private void btnSalvar_Click(object sender, EventArgs e)
//     {
//         Configuracao conf = new Configuracao();
//         Configuracao confBD = new Configuracao();
//
//         try
//         {
//             conf = confBD.GetLastValidConfExercicio();
//
//             if (conf.Ativo == " ")
//             {
//                 conf.Ativo = "X";
//             }
//             else
//             {
//                 conf.Ativo = " ";
//             }
//
//             if (chkStatus.Checked == true)
//             {
//                 conf.Ativo = "X";
//             }
//             conf.Mes = (cmbCompetencia.SelectedIndex + 1).ToString();
//             conf.SalvarConfExercicio(conf);
//
//             if (chkContaDefault.Checked == true)
//             {
//                 conf.ContaDefault = cmbContas.SelectedValue.ToString();
//                 conf.SalvarContaDefault(conf);
//             }
//
//             this.ValidaFormEmail();
//             conf.EmailDestinatario = txtDestinatario.Text;
//             conf.EmailRemetente = txtRemetente.Text;
//             conf.Senha = txtSenha.Text;
//             conf.Porta = Convert.ToInt16(txtPorta.Text);
//             conf.SmtpServer = txtSmtpSever.Text;
//             conf.DiasAntNotificacao = int.Parse(txtDiasNotificacao.Text);
//             conf.SalvarConfEmail(conf);
//             MessageBox.Show("Configuração salva com sucesso.", "Sucesso", MessageBoxButtons.OK, MessageBoxIcon.Exclamation, MessageBoxDefaultButton.Button1);
//         }
//         catch (Exception ex)
//         {
//             MessageBox.Show(ex.Message, "Erro", MessageBoxButtons.OKCancel, MessageBoxIcon.Error, MessageBoxDefaultButton.Button1);
//         }
//     }
//
//     private void cmbBancos_SelectedIndexChanged(object sender, EventArgs e)
//     {
//      
//     }
//
//     private void cmbBancos_SelectedIndexChanged_1(object sender, EventArgs e)
//     {
//         ContaCapital contaBD = new ContaCapital();
//         List<ContaCapital> listaContas = new List<ContaCapital>();
//         listaContas = contaBD.Listar().Where(it => it.CodBanco == cmbBancos.SelectedValue.ToString()).ToList();
//         Dictionary<string, string> test = new Dictionary<string, string>();
//
//         if (listaContas.Count > 0)
//         {
//             foreach (ContaCapital conta in listaContas)
//             {
//                 test.Add(conta.CodConta, conta.CodConta);
//             }
//             cmbContas.DataSource = new BindingSource(test, null);
//             cmbContas.DisplayMember = "Key";
//             cmbContas.ValueMember = "Value";
//         }
//     }
//     private bool ValidaEnderecoEmail(string enderecoEmail)
//     {
//         bool validaa = false;
//         //define a expressão regulara para validar o email
//         string texto_Validar = enderecoEmail;
//         Regex expressaoRegex = new Regex(@"\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}");
//
//         // testa o email com a expressão
//         if (expressaoRegex.IsMatch(texto_Validar))
//         {
//             // o email é valido
//             return true;
//         }
//         else
//         {
//             throw new InvalidOperationException("O email cadastrado é inválido.");
//         }
//         return validaa;
//     }
//     private void ValidaFormEmail()
//     {
//         this.ValidaEnderecoEmail(txtRemetente.Text);
//         this.ValidaEnderecoEmail(txtDestinatario.Text);
//
//         if(string.IsNullOrEmpty(txtDiasNotificacao.Text))
//           throw new InvalidOperationException("Preencha o número de dias para notificar as despesas a vencer.");
//
//     }
//     private void CarregaConfEmail()
//     {
//         Configuracao confBD = new Configuracao();
//         Configuracao conf = new Configuracao();
//
//         conf = confBD.GetConfEmail();
//         txtSmtpSever.Text = conf.SmtpServer;
//         txtDestinatario.Text = conf.EmailDestinatario;
//         txtSenha.Text = conf.Senha;
//         txtRemetente.Text = conf.EmailRemetente;
//         txtPorta.Text = conf.Porta.ToString();
//         txtDiasNotificacao.Text = conf.DiasAntNotificacao.ToString();
//     }
//
//     private void rdbBlue_CheckedChanged(object sender, EventArgs e)
//     {
//         frmPrincipal frm = new frmPrincipal();
//         frm.BackColor = System.Drawing.Color.Blue;
//     }
//
//     private void rdbRed_CheckedChanged(object sender, EventArgs e)
//     {
//         frmPrincipal frm = new frmPrincipal();
//         frm.BackColor = System.Drawing.Color.DarkRed;
//     }
//
//     private void rdbGreen_CheckedChanged(object sender, EventArgs e)
//     {
//         frmPrincipal frm = new frmPrincipal();
//         frm.BackColor = System.Drawing.Color.Green;
//     }
//
//     private void rdbGray_CheckedChanged(object sender, EventArgs e)
//     {
//         frmPrincipal frm = new frmPrincipal();
//         frm.BackColor = System.Drawing.Color.Gray;
//     }
}
