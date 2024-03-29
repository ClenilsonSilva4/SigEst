package AplicacaoMercado.main;

import java.util.Scanner;

import AplicacaoMercado.dao.EstocadorDAOMySQL;
import AplicacaoMercado.dao.GestorDAOMySQL;
import AplicacaoMercado.dao.ProdutosDAOMySQL;
import AplicacaoMercado.domain.RegraAdicaoProduto;
import AplicacaoMercado.entities.Estocador;
import AplicacaoMercado.entities.Produto;
import AplicacaoMercado.service.login.LoginService;
import AplicacaoMercado.service.login.LoginServiceInterface;
import AplicacaoMercado.view.MainView;
import exception.*;
import framework.controller.GerenciadorAvaliador;
import framework.controller.GerenciadorGestor;
import framework.controller.GerenciadorRecurso;
import framework.domain.*;

public class Main {
    public static void main(String[] args) {
        LoginServiceInterface login = new LoginService();
        MainView mv = new MainView();
        GerenciadorGestor gerenciamentoGestor = new GerenciadorGestor(new GestorDAOMySQL());
        GerenciadorAvaliador gerenciamentoEstocador = new GerenciadorAvaliador(new EstocadorDAOMySQL(), gerenciamentoGestor);
        GerenciadorRecurso gerenciamentoProduto = new GerenciadorRecurso(new ProdutosDAOMySQL(), new RegraAdicaoProduto(), gerenciamentoGestor);

        Scanner scanner = new Scanner(System.in);
        String nome, email, senha, validade, dataAdmissao;
        boolean menuPrincipal = true, menuSecundario = true;
        int tipoUsuario = 0, opcaoPrincipal, opcaoSecundaria;
        Object usuario;

        // Login do usu�rio
        while(true) {
            mv.header();
            mv.textCenter("LOGIN");
            mv.border();

            email = mv.inputString("Digite seu e-mail: ");
            senha = mv.inputString("Digite sua senha: ");

            try {
                usuario = login.checarLogin(email, senha);
                if(usuario != null) {
                    break;
                }
            } catch (UserNotFoundException | DBUnavailable | InvalidData e) {
                mv.textBox(e.getMessage());
                System.out.println();
            }
        }

        if(usuario instanceof Avaliador) {
            tipoUsuario = 1;
        } else if (usuario instanceof Gestor) {
            tipoUsuario = 2;
        }
        
        switch(tipoUsuario) {
            // Visao do usuario Professor
            case 1:
            	Estocador professor = (Estocador) usuario;
                while(menuPrincipal) {
                	mv.header();
                	mv.textCenter("Bem-vindo(a) Professor | " + professor.getNome());
                	mv.border();
                	mv.text("1 - Consultar Funcionario");
                	mv.text("2 - Consultar Turma");
                	mv.text("3 - Presen�a");
                	mv.text("4 - Chat");
                	mv.text("5 - Enviar Mensagem");
                	mv.text("6 - Consultar Mensagem");
                	mv.text("9 - Voltar");
                	mv.textBox("0 - Encerrar Programa");
                	opcaoPrincipal = mv.inputOpcao();
                	mv.borderln();
                	
                    int opcao;
                    switch(opcaoPrincipal) {
                        case 1:
                            try {
                                Avaliador consultaAvaliador = gerenciamentoEstocador.buscarAvaliadorPorId(professor.getId());

                                mv.header();
                                mv.textCenter("Bem-Vindo(a) Professor | " + professor.getNome());
                                mv.border();
                                mv.text("ID do Professor: " + consultaAvaliador.getId());
                                mv.text("Nome do Professor: " + consultaAvaliador.getNome());
                                mv.text("E-mail do Professor: " + consultaAvaliador.getEmail());
                                mv.borderln();
                            } catch (DBUnavailable e) {
                            	do {
                                    mv.header();
                                    mv.textCenter("Bem-vindo(a) Professor(a) | " + professor.getNome());
                                    mv.border();
                                    mv.text(e.getMessage());
                                    mv.text("1 - Tentar Novamente");
                                    mv.text("2 - Cancelar");
                                    opcao = mv.inputOpcao();
                                    mv.borderln();
                                } while (opcao != 2 && opcao != 1);
                                mv.textBox(e.getMessage());
                            } catch (UserNotFoundException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 2:
                            /*try {
                                ConjuntoRecurso consultaConjuntoRecurso = professor.consultarTurma(1);

                                mv.header();
                                mv.textCenter("Bem-Vindo(a) | Estudante");
                                mv.border();
                                mv.text("ID: " + consultaConjuntoRecurso.getIdTurma());
                                mv.text("Nome da Turma:" + consultaConjuntoRecurso.getNomeDisciplina());
                                mv.text("Capacidade de Estudantes: " + consultaConjuntoRecurso.getCapacidadeAlunos());
                                mv.borderln();
                            } catch(UserNotFoundException | DBUnavailable e) {
                                mv.textBox(e.getMessage());
                            }*/
                            break;
                        case 3:
                            while(menuSecundario) {
                            	mv.header();
                            	mv.textCenter("Bem-vindo(a) | " + professor.getNome());
                            	mv.border();
                            	mv.text("1 - Inserir Presen�a");
                            	mv.text("2 - Consultar Presen�a");
                            	mv.text("3 - Alterar Presen�a");
                            	mv.text("9 - Voltar");
                            	mv.textBox("0 - Encerrar Programa");
                            	opcaoSecundaria = mv.inputOpcao();
                            	mv.borderln();
                            	
                                switch(opcaoSecundaria) {
                                    case 1:
                                        System.out.println("Inseriu Presen�a");
                                        break;
                                    case 2:
                                        System.out.println("Consultou Presen�a");
                                        break;
                                    case 3:
                                        System.out.println("Alterou Presen�a");
                                        break;
                                    case 9:
                                        menuSecundario = false;
                                        break;
                                    case 0: 
                                    	menuPrincipal = false;
                                    	menuSecundario = false;
                                    	break;
                                    default:
                                        mv.text("Valor inv�lido");
                                        mv.borderln();
                                }
                            }
                            menuSecundario = true;
                            break;
                        case 4:
                            while(menuSecundario) {
                            	mv.header();
                            	mv.textCenter("Bem-vindo(a) | " + professor.getNome());
                            	mv.border();
                            	mv.text("1 - Criar Chat");
                            	mv.text("2 - Consultar Chat");
                            	mv.text("3 - Alterar Chat");
                            	mv.text("4 - Remover Chat");
                            	mv.text("9 - Voltar");
                            	mv.textBox("0 - Encerrar Programa");
                            	opcaoSecundaria = mv.inputOpcao();
                            	mv.borderln();                            	
                      
                                switch(opcaoSecundaria) {
                                    case 1:
                                        System.out.println("Criou Chat");
                                        break;
                                    case 2:
                                        System.out.println("Consultou Chat");
                                        break;
                                    case 3:
                                        System.out.println("Alterou Chat");
                                        break;
                                    case 4:
                                        System.out.println("Removeu Chat");
                                        break;
                                    case 9:
                                        menuSecundario = false;
                                        break;
                                    case 0: 
                                    	menuPrincipal = false;
                                    	menuSecundario = false;
                                    	break;
                                    default:
                                        mv.text("Valor inv�lido");
                                        mv.borderln();
                                }
                            }
                            menuSecundario = true;
                            break;
                        case 5:
                        case 6:
                            mv.text("Funcionalidade n�o implementada");
                        	mv.borderln();                          
                            break;
                        case 0:
                            menuPrincipal = false;
                            break;
                        default:
                        	mv.text("Valor inv�lido");
                            mv.borderln();
                    }
                }
                break;
            // Visao do usuario Gestor
            case 2:
                Gestor gestor = (Gestor) usuario;//new Gestor(445, "Jo�o", "teste", "123456");

                while(menuPrincipal) {
                    mv.header();
                    mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                    mv.border();
                    mv.text("1 - Estudante");
                    mv.text("2 - Professor");
                    mv.text("3 - Gestor");
                    mv.text("4 - Turma");
                    mv.text("5 - Presen�a");
                    mv.text("6 - Chat");
                    mv.text("9 - Voltar");
                    mv.textBox("0 - Encerrar Programa");
                    opcaoPrincipal = mv.inputOpcao();
                    mv.borderln();

                    int opcao = 1;
                    switch(opcaoPrincipal) {
                        case 1: // Menu Secundario de estudante
                            while(menuSecundario) {
                                mv.header();
                                mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                mv.border();
                                mv.text("1 - Inserir Estudante");
                                mv.text("2 - Consultar Estudante");
                                mv.text("3 - Alterar Estudante");
                                mv.text("4 - Remover Estudante");
                                mv.text("9 - Voltar");
                                mv.textBox("0 - Encerrar Programa");
                                opcaoSecundaria = mv.inputOpcao();
                                mv.borderln();

                                switch(opcaoSecundaria) {
                                    case 1:
                                        while (opcao == 1) {
                                            mv.header();
                                            mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                            mv.border();
                                            nome = mv.inputString("Digite o nome do estudante: ");
                                            validade = mv.inputString("Digite o curso do estudante: ");

                                            try {
                                                Produto novoProduto = new Produto(214321, nome, false, validade);

                                                gerenciamentoProduto.adicionarRecurso(gestor, novoProduto);

                                                mv.header();
                                                mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                mv.border();
                                                mv.text("Produto cadastrado com sucesso");
                                                mv.borderln();
                                                opcao = 2;
                                            } catch (DBUnavailable | ChangeNotMade | UserWithoutPermission e) {
                                                do {
                                                    mv.header();
                                                    mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                    mv.border();
                                                    mv.text(e.getMessage());
                                                    mv.text("1 - Tentar Novamente");
                                                    mv.text("2 - Cancelar");
                                                    opcao = mv.inputOpcao();
                                                    mv.borderln();
                                                } while (opcao != 2 && opcao != 1);
                                            }
                                        }
                                        break;
                                    case 2:
                                        while (opcao == 1) {
                                            mv.header();
                                            mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                            mv.border();
                                            int idEstudante = Integer.parseInt(mv.inputString("Digite o ID do estudante que quer consultar: "));

                                            try {
                                                Produto consultaRecurso = (Produto) gerenciamentoProduto.buscarRecursoPorId(idEstudante);

                                                mv.header();
                                                mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                mv.border();
                                                mv.text("ID do Estudante: " + consultaRecurso.getId());
                                                mv.text("Nome do Estudante: " + consultaRecurso.getNome());
                                                mv.text("E-mail do Estudante: " + consultaRecurso.getValidade());
                                                mv.borderln();
                                                opcao = 2;
                                            } catch (DBUnavailable | UserNotFoundException e) {
                                                do {
                                                    mv.header();
                                                    mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                    mv.border();
                                                    mv.text(e.getMessage());
                                                    mv.text("1 - Tentar Novamente");
                                                    mv.text("2 - Cancelar");
                                                    opcao = mv.inputOpcao();
                                                    mv.borderln();
                                                } while (opcao != 2 && opcao != 1);
                                            }
                                        }
                                        break;
                                    case 3:
                                        while (opcao == 1) {
                                            mv.header();
                                            mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                            mv.border();
                                            int idEstudante = Integer.parseInt(mv.inputString("Digite o ID do estudante que quer alterar"));
                                            mv.border();
                                            mv.text("Dica: Preencha apenas os campos que deseja alterar deixando os outros vazios");

                                            nome = mv.inputString("Digite o novo nome do estudante: ");
                                            validade = mv.inputString("Digite a senha do estudante: ");

                                            try {
                                                Produto novoEstudante = new Produto(idEstudante, nome, false, validade);

                                                gerenciamentoProduto.alterarRecurso(gestor, novoEstudante);

                                                mv.header();
                                                mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                mv.border();
                                                mv.text("Estudante alterado com sucesso");
                                                mv.borderln();
                                                opcao = 2;
                                            } catch (DBUnavailable | ChangeNotMade | UserWithoutPermission e) {
                                                do {
                                                    mv.header();
                                                    mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                    mv.border();
                                                    mv.text(e.getMessage());
                                                    mv.text("1 - Tentar Novamente");
                                                    mv.text("2 - Cancelar");
                                                    opcao = mv.inputOpcao();
                                                    mv.borderln();
                                                } while (opcao != 2 && opcao != 1);
                                            }
                                        }
                                        break;
                                    case 4:
                                        while (opcao == 1) {
                                            mv.header();
                                            mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                            mv.border();
                                            int idEstudante = Integer.parseInt(mv.inputString("Digite o ID do estudante que quer remover: "));

                                            try {
                                                gerenciamentoProduto.removerRecurso(gestor, idEstudante);

                                                mv.header();
                                                mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                mv.border();
                                                mv.text("Estudante removido com sucesso");
                                                mv.borderln();
                                                opcao = 2;
                                            } catch (DBUnavailable | UserWithoutPermission | ChangeNotMade e) {
                                                do {
                                                    mv.header();
                                                    mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                    mv.border();
                                                    mv.text(e.getMessage());
                                                    mv.text("1 - Tentar Novamente");
                                                    mv.text("2 - Cancelar");
                                                    opcao = mv.inputOpcao();
                                                    mv.borderln();
                                                } while (opcao != 2 && opcao != 1);
                                            }
                                        }
                                        break;
                                    case 9:
                                        menuSecundario = false;
                                        break;
                                    case 0:
                                        menuPrincipal = false;
                                        menuSecundario = false;
                                        break;
                                    default:
                                        mv.text("Op��o Inv�lida");
                                }
                            }
                            menuSecundario = true;
                            break;
                        case 2: // Menu Secundario de funcionario
                        	while(menuSecundario) {
                                mv.header();
                                mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                mv.border();
                                mv.text("1 - Inserir Funcionario");
                                mv.text("2 - Consultar Funcionario");
                                mv.text("3 - Alterar Funcionario");
                                mv.text("4 - Remover Funcionario");
                                mv.text("9 - Voltar");
                                mv.textBox("0 - Encerrar Programa");
                                opcaoSecundaria = mv.inputOpcao();
                                mv.borderln();

                                switch(opcaoSecundaria) {
                                    case 1:
                                        while (opcao == 1) {
                                            mv.header();
                                            mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                            mv.border();
                                            nome = mv.inputString("Digite o nome do funcionario: ");
                                            email = mv.inputString("Digite o email do funcionario: ");
                                            senha = mv.inputString("Digite a senha do funcionario: ");
                                            dataAdmissao = mv.inputString("Digite a data de admiss�o do funcionario: ");

                                            try {
                                                Estocador novoProfessor = new Estocador(5166, nome, email, senha, dataAdmissao);
                                                gerenciamentoEstocador.adicionarAvaliador(gestor, novoProfessor);

                                                mv.header();
                                                mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                mv.border();
                                                mv.text("Estocador cadastrado com sucesso");
                                                mv.borderln();
                                                opcao = 2;
                                            } catch (DBUnavailable | ChangeNotMade | UserWithoutPermission e) {
                                                do {
                                                    mv.header();
                                                    mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                    mv.border();
                                                    mv.text(e.getMessage());
                                                    mv.text("1 - Tentar Novamente");
                                                    mv.text("2 - Cancelar");
                                                    opcao = mv.inputOpcao();
                                                    mv.borderln();
                                                } while (opcao != 2 && opcao != 1);
                                            }
                                        }
                                        break;
                                    case 2:
                                        while (opcao == 1) {
                                            mv.header();
                                            mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                            mv.border();
                                            int idProfessor = Integer.parseInt(mv.inputString("Digite o ID do funcionario que quer consultar"));

                                            try {
                                                Estocador consultaAvaliador = (Estocador) gerenciamentoEstocador.buscarAvaliadorPorId(idProfessor);

                                                mv.header();
                                                mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                mv.border();
                                                mv.text("ID do Estocador: " + consultaAvaliador.getId());
                                                mv.text("Nome do Estocador: " + consultaAvaliador.getNome());
                                                mv.text("E-mail do Estocador: " + consultaAvaliador.getEmail());
                                                mv.text("Data de Admiss�o do Estocador: " + consultaAvaliador.getDataAdmissao());
                                                mv.borderln();
                                                opcao = 2;
                                            } catch (DBUnavailable | UserNotFoundException e) {
                                                do {
                                                    mv.header();
                                                    mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                    mv.border();
                                                    mv.text(e.getMessage());
                                                    mv.text("1 - Tentar Novamente");
                                                    mv.text("2 - Cancelar");
                                                    opcao = mv.inputOpcao();
                                                    mv.borderln();
                                                } while (opcao != 2 && opcao != 1);
                                            }
                                        }
                                        break;
                                    case 3:
                                        while (opcao == 1) {
                                            mv.header();
                                            mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                            mv.border();
                                            int idProfessor = Integer.parseInt(mv.inputString("Digite o ID do funcionario que quer alterar"));
                                            mv.border();
                                            mv.text("Dica: Preencha apenas os campos que deseja alterar deixando os outros vazios");

                                            nome = mv.inputString("Digite o novo nome do funcionario: ");
                                            email = mv.inputString("Digite o novo email do funcionario: ");
                                            senha = mv.inputString("Digite a nova senha do funcionario: ");
                                            dataAdmissao = mv.inputString("Digite a data de admiss�o do funcionario: ");

                                            try {
                                                Estocador alterarProfessor = new Estocador(idProfessor, nome, email, senha, dataAdmissao);
                                                gerenciamentoEstocador.alterarAvaliador(gestor, alterarProfessor);

                                                mv.header();
                                                mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                mv.border();
                                                mv.text("Professor alterado com sucesso");
                                                mv.borderln();
                                                opcao = 2;
                                            } catch (DBUnavailable | ChangeNotMade | UserWithoutPermission e) {
                                                do {
                                                    mv.header();
                                                    mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                    mv.border();
                                                    mv.text(e.getMessage());
                                                    mv.text("1 - Tentar Novamente");
                                                    mv.text("2 - Cancelar");
                                                    opcao = mv.inputOpcao();
                                                    mv.borderln();
                                                } while (opcao != 2 && opcao != 1);
                                            }
                                        }
                                        break;
                                    case 4:
                                        while (opcao == 1) {
                                            mv.header();
                                            mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                            mv.border();
                                            int idEstocador = Integer.parseInt(mv.inputString("Digite o ID do funcionario que quer remover"));

                                            try {
                                                gerenciamentoEstocador.removerAvaliador(gestor, idEstocador);

                                                mv.header();
                                                mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                mv.border();
                                                mv.text("Professor removido com sucesso");
                                                mv.borderln();
                                                opcao = 2;
                                            } catch (DBUnavailable | UserWithoutPermission | ChangeNotMade e) {
                                                do {
                                                    mv.header();
                                                    mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                    mv.border();
                                                    mv.text(e.getMessage());
                                                    mv.text("1 - Tentar Novamente");
                                                    mv.text("2 - Cancelar");
                                                    opcao = mv.inputOpcao();
                                                    mv.borderln();
                                                } while (opcao != 2 && opcao != 1);
                                            }
                                        }
                                        break;
                                    case 9:
                                        menuSecundario = false;
                                        break;
                                    case 0:
                                        menuPrincipal = false;
                                        menuSecundario = false;
                                        break;
                                    default:
                                        mv.text("Op��o Inv�lida");
                                }
                            }
                            menuSecundario = true;
                            break;
                        case 3: // Menu Secundario de gestor
                        	while(menuSecundario) {
                                mv.header();
                                mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                mv.border();
                                mv.text("1 - Inserir Gestor");
                                mv.text("2 - Consultar Gestor");
                                mv.text("3 - Alterar Gestor");
                                mv.text("4 - Remover Gestor");
                                mv.text("9 - Voltar");
                                mv.textBox("0 - Encerrar Programa");
                                opcaoSecundaria = mv.inputOpcao();
                                mv.borderln();

                                switch(opcaoSecundaria) {
                                    case 1:
                                        while (opcao == 1) {
                                            mv.header();
                                            mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                            mv.border();
                                            nome = mv.inputString("Digite o nome do gestor: ");
                                            email = mv.inputString("Digite o email do gestor: ");
                                            senha = mv.inputString("Digite a senha do gestor: ");

                                            try {
                                                Gestor novoGestor = new Gestor(4551, nome, email, senha);
                                                gerenciamentoGestor.adicionarGestor(gestor, novoGestor);

                                                mv.header();
                                                mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                mv.border();
                                                mv.text("Gestor cadastrado com sucesso");
                                                mv.borderln();
                                                opcao = 2;
                                            } catch (DBUnavailable | EmailAlreadyInUse | ChangeNotMade | UserWithoutPermission e) {
                                                do {
                                                    mv.header();
                                                    mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                    mv.border();
                                                    mv.text(e.getMessage());
                                                    mv.text("1 - Tentar Novamente");
                                                    mv.text("2 - Cancelar");
                                                    opcao = mv.inputOpcao();
                                                    mv.borderln();
                                                } while (opcao != 2 && opcao != 1);
                                            }
                                        }
                                        break;
                                    case 2:
                                        while (opcao == 1) {
                                            mv.header();
                                            mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                            mv.border();
                                            int idGestor = Integer.parseInt(mv.inputString("Digite o ID do gestor que quer consultar"));

                                            try {
                                                Gestor consultaGestor = gerenciamentoGestor.buscarGestorPorId(idGestor);

                                                mv.header();
                                                mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                mv.border();
                                                mv.text("ID do Gestor: " + consultaGestor.getId());
                                                mv.text("Nome do Gestor: " + consultaGestor.getNome());
                                                mv.text("E-mail do Gestor: " + consultaGestor.getEmail());
                                                mv.borderln();
                                                opcao = 2;
                                            } catch (DBUnavailable | UserNotFoundException e) {
                                                do {
                                                    mv.header();
                                                    mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                    mv.border();
                                                    mv.text(e.getMessage());
                                                    mv.text("1 - Tentar Novamente");
                                                    mv.text("2 - Cancelar");
                                                    opcao = mv.inputOpcao();
                                                    mv.borderln();
                                                } while (opcao != 2 && opcao != 1);
                                            }
                                        }
                                        break;
                                    case 3:
                                        while (opcao == 1) {
                                            mv.header();
                                            mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                            mv.border();
                                            int idGestor = Integer.parseInt(mv.inputString("Digite o ID do gestor que quer alterar"));
                                            mv.border();
                                            mv.text("Dica: Preencha apenas os campos que deseja alterar deixando os outros vazios");

                                            nome = mv.inputString("Digite o novo nome do gestor: ");
                                            email = mv.inputString("Digite o novo email do gestor: ");
                                            senha = mv.inputString("Digite a nova senha do gestor: ");

                                            try {
                                                Gestor alterarGestor = new Gestor(idGestor, nome, email, senha);
                                                gerenciamentoGestor.alterarGestor(gestor, alterarGestor);

                                                mv.header();
                                                mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                mv.border();
                                                mv.text("Gestor alterado com sucesso");
                                                mv.borderln();
                                                opcao = 2;
                                            } catch (DBUnavailable | ChangeNotMade | UserWithoutPermission | EmailAlreadyInUse e) {
                                                do {
                                                    mv.header();
                                                    mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                    mv.border();
                                                    mv.text(e.getMessage());
                                                    mv.text("1 - Tentar Novamente");
                                                    mv.text("2 - Cancelar");
                                                    opcao = mv.inputOpcao();
                                                    mv.borderln();
                                                } while (opcao != 2 && opcao != 1);
                                            }
                                        }
                                        break;
                                    case 4:
                                        while (opcao == 1) {
                                            mv.header();
                                            mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                            mv.border();
                                            long idGestor = Integer.parseInt(mv.inputString("Digite o ID do gestor que quer remover"));

                                            try {
                                                gerenciamentoGestor.removerGestor(gestor, idGestor);

                                                mv.header();
                                                mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                mv.border();
                                                mv.text("Gestor removido com sucesso");
                                                mv.borderln();
                                                opcao = 2;
                                            } catch (DBUnavailable | UserWithoutPermission | ChangeNotMade e) {
                                                do {
                                                    mv.header();
                                                    mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                    mv.border();
                                                    mv.text(e.getMessage());
                                                    mv.text("1 - Tentar Novamente");
                                                    mv.text("2 - Cancelar");
                                                    opcao = mv.inputOpcao();
                                                    mv.borderln();
                                                } while (opcao != 2 && opcao != 1);
                                            }
                                        }
                                        break;
                                    case 9:
                                        menuSecundario = false;
                                        break;
                                    case 0:
                                        menuPrincipal = false;
                                        menuSecundario = false;
                                        break;
                                    default:
                                        mv.text("Op��o Inv�lida");
                                }
                            }
                            menuSecundario = true;
                            break;
                        case 4: // Menu Secundario de turma
                        	/*while(menuSecundario) {
                                mv.header();
                                mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                mv.border();
                                mv.text("1 - Inserir Turma");
                                mv.text("2 - Consultar Turma");
                                mv.text("3 - Alterar Turma");
                                mv.text("4 - Remover Turma");
                                mv.text("9 - Voltar");
                                mv.textBox("0 - Encerrar Programa");
                                opcaoSecundaria = mv.inputOpcao();
                                mv.borderln();

                                switch(opcaoSecundaria) {
                                    case 1:
                                        while (opcao == 1) {
                                            mv.header();
                                            mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                            mv.border();
                                            nome = mv.inputString("Digite o nome da turma: ");
                                            capacidade = Integer.parseInt(mv.inputString("Digite a capacidade da turma: "));

                                            try {
                                                gestor.inserirTurma(usuario.getIdUsuario(), nome, capacidade);

                                                mv.header();
                                                mv.textCenter("Bem-vindo(a) Gestor(a) | " + usuario.getNomeUsuario());
                                                mv.border();
                                                mv.text("Turma cadastrado com sucesso");
                                                mv.borderln();
                                                opcao = 2;
                                            } catch (DBUnavailable | ChangeNotMade | UserWithoutPermission e) {
                                                do {
                                                    mv.header();
                                                    mv.textCenter("Bem-vindo(a) Gestor(a) | " + usuario.getNomeUsuario());
                                                    mv.border();
                                                    mv.text(e.getMessage());
                                                    mv.text("1 - Tentar Novamente");
                                                    mv.text("2 - Cancelar");
                                                    opcao = mv.inputOpcao();
                                                    mv.borderln();
                                                } while (opcao != 2 && opcao != 1);
                                            }
                                        }
                                        break;
                                    case 2:
                                        while (opcao == 1) {
                                            mv.header();
                                            mv.textCenter("Bem-vindo(a) Gestor(a) | " + usuario.getNomeUsuario());
                                            mv.border();
                                            int idTurma = Integer.parseInt(mv.inputString("Digite o ID do gestor que quer consultar"));

                                            try {
                                                ConjuntoRecurso consultaConjuntoRecurso = gestor.consultarTurma(idTurma);

                                                mv.header();
                                                mv.textCenter("Bem-vindo(a) Gestor(a) | " + usuario.getNomeUsuario());
                                                mv.border();
                                                mv.text("ID da Turma: " + consultaConjuntoRecurso.getIdTurma());
                                                mv.text("Nome da Turma: " + consultaConjuntoRecurso.getNomeDisciplina());
                                                mv.text("Capacidade de alunos da Turma: " + consultaConjuntoRecurso.getCapacidadeAlunos());
                                                mv.borderln();
                                                opcao = 2;
                                            } catch (DBUnavailable | UserNotFoundException e) {
                                                do {
                                                    mv.header();
                                                    mv.textCenter("Bem-vindo(a) Gestor(a) | " + usuario.getNomeUsuario());
                                                    mv.border();
                                                    mv.text(e.getMessage());
                                                    mv.text("1 - Tentar Novamente");
                                                    mv.text("2 - Cancelar");
                                                    opcao = mv.inputOpcao();
                                                    mv.borderln();
                                                } while (opcao != 2 && opcao != 1);
                                            }
                                        }
                                        break;
                                    case 3:
                                        while (opcao == 1) {
                                            mv.header();
                                            mv.textCenter("Bem-vindo(a) Gestor(a) | " + usuario.getNomeUsuario());
                                            mv.border();
                                            int idTurma = Integer.parseInt(mv.inputString("Digite o ID do turma que quer alterar"));
                                            mv.border();
                                            mv.text("Dica: Preencha apenas os campos que deseja alterar deixando os outros vazios");

                                            nome = mv.inputString("Digite o novo nome da turma: ");
                                            capacidade = Integer.parseInt(mv.inputString("Digite a nova capacidade da turma: "));

                                            try {
                                                gestor.alterarTurma(idTurma, nome, capacidade, usuario.getIdUsuario());

                                                mv.header();
                                                mv.textCenter("Bem-vindo(a) Gestor(a) | " + usuario.getNomeUsuario());
                                                mv.border();
                                                mv.text("Turma alterada com sucesso");
                                                mv.borderln();
                                                opcao = 2;
                                            } catch (DBUnavailable | ChangeNotMade | UserWithoutPermission e) {
                                                do {
                                                    mv.header();
                                                    mv.textCenter("Bem-vindo(a) Gestor(a) | " + usuario.getNomeUsuario());
                                                    mv.border();
                                                    mv.text(e.getMessage());
                                                    mv.text("1 - Tentar Novamente");
                                                    mv.text("2 - Cancelar");
                                                    opcao = mv.inputOpcao();
                                                    mv.borderln();
                                                } while (opcao != 2 && opcao != 1);
                                            }
                                        }
                                        break;
                                    case 4:
                                        while (opcao == 1) {
                                            mv.header();
                                            mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                            mv.border();
                                            int idTurma = Integer.parseInt(mv.inputString("Digite o ID da turma que quer remover: "));

                                            try {
                                                gestor.removerTurma(idTurma, usuario.getIdUsuario());

                                                mv.header();
                                                mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                mv.border();
                                                mv.text("Turma removido com sucesso");
                                                mv.borderln();
                                                opcao = 2;
                                            } catch (DBUnavailable | UserWithoutPermission | ChangeNotMade e) {
                                                do {
                                                    mv.header();
                                                    mv.textCenter("Bem-vindo(a) Gestor(a) | " + gestor.getNome());
                                                    mv.border();
                                                    mv.text(e.getMessage());
                                                    mv.text("1 - Tentar Novamente");
                                                    mv.text("2 - Cancelar");
                                                    opcao = mv.inputOpcao();
                                                    mv.borderln();
                                                } while (opcao != 2 && opcao != 1);
                                            }
                                        }
                                        break;
                                    case 9:
                                        menuSecundario = false;
                                        break;
                                    case 0:
                                        menuPrincipal = false;
                                        menuSecundario = false;
                                        break;
                                    default:
                                        mv.text("Op��o Inv�lida");
                                }
                            }
                            menuSecundario = true;*/
                            break;
                        case 5: // Menu Secundario de mensagem
                            while(menuSecundario) {
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("|        SiGest - Sistema de Gerenciamento Estudantil         |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("|                         Mensagem                            |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("| 1 - Inserir Mensagem                                        |");
                                System.out.println("| 2 - Consultar Mensagem                                      |");
                                System.out.println("| 3 - Alterar Mensagem                                        |");
                                System.out.println("| 4 - Remover Mensagem                                        |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("| 9 - Voltar                                                  |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.print("| Sua op��o: ");
                                opcaoSecundaria = scanner.nextInt();
                                System.out.println("+-------------------------------------------------------------+\n");
                                switch(opcaoSecundaria) {
                                    case 1:
                                        System.out.println("Inseriu Mensagem");
                                        break;
                                    case 2:
                                        System.out.println("Consultou Mensagem");
                                        break;
                                    case 3:
                                        System.out.println("Alterou Mensagem");
                                        break;
                                    case 4:
                                        System.out.println("Removeu Mensagem");
                                        break;
                                    case 9:
                                        menuSecundario = false;
                                        break;
                                    default:
                                        mv.text("Op��o Inv�lida");
                                }
                            }
                            menuSecundario = true;
                            break;
                        case 6: // Menu Secundario de chat
                            while(menuSecundario) {
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("|        SiGest - Sistema de Gerenciamento Estudantil         |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("|                         Turma                               |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("| 1 - Inserir Turma                                           |");
                                System.out.println("| 2 - Consultar Turma                                         |");
                                System.out.println("| 3 - Alterar Turma                                           |");
                                System.out.println("| 4 - Remover Turma                                           |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("| 9 - Voltar                                                  |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.print("| Sua op��o: ");
                                opcaoSecundaria = scanner.nextInt();
                                System.out.println("+-------------------------------------------------------------+\n");
                                switch(opcaoSecundaria) {
                                    case 1:
                                        System.out.println("Inseriu Turma");
                                        break;
                                    case 2:
                                        System.out.println("Consultou Turma");
                                        break;
                                    case 3:
                                        System.out.println("Alterou Turma");
                                        break;
                                    case 4:
                                        System.out.println("Removeu Turma");
                                        break;
                                    case 9:
                                        menuSecundario = false;
                                        break;
                                    default:
                                        mv.text("Op��o Inv�lida");
                                }
                            }
                            menuSecundario = true;
                            break;
                        case 7:
                            while(menuSecundario) {
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("|        SiGest - Sistema de Gerenciamento Estudantil         |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("|                         Presen�a                            |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("| 1 - Inserir Presen�a                                        |");
                                System.out.println("| 2 - Consultar Presen�a                                      |");
                                System.out.println("| 3 - Alterar Presen�a                                        |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("| 9 - Voltar                                                  |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.print("| Sua op��o: ");
                                opcaoSecundaria = scanner.nextInt();
                                System.out.println("+-------------------------------------------------------------+\n");
                                switch(opcaoSecundaria) {
                                    case 1:
                                        System.out.println("Inseriu Presen�a");
                                        break;
                                    case 2:
                                        System.out.println("Consultou Presen�a");
                                        break;
                                    case 3:
                                        System.out.println("Alterou Presen�a");
                                        break;
                                    case 9:
                                        menuSecundario = false;
                                        break;
                                    default:
                                        mv.text("Op��o Inv�lida");
                                }
                            }
                            menuSecundario = true;
                            break;
                        case 0:
                            menuPrincipal = false;
                            break;
                        default:
                            mv.text("Op��o Inv�lida");
                    }
                }
                break;
        }

        scanner.close();
    }
}