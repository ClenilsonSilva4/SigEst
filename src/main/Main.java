package main;

import java.util.Scanner;

import entities.Estudante;
import entities.Presenca;
import entities.Professor;
import entities.Turma;
import entities.Usuario;
import exception.DBUnavailable;
import exception.InvalidData;
import exception.UserNotFoundException;
import service.login.LoginService;
import service.login.LoginServiceInterface;
import service.usuario.estudante.EstudanteService;
import service.usuario.estudante.EstudanteServiceInterface;
import service.usuario.professor.ProfessorService;
import service.usuario.professor.ProfessorServiceInterface;
import view.MainView;

public class Main {
    public static void main(String[] args) {
        LoginServiceInterface login = new LoginService();
        
        MainView mv = new MainView();

        boolean menuPrincipal = true, menuSecundario = true;
        String email, senha;
        int tipoUsuario, opcaoPrincipal, opcaoSecundaria;

        Usuario usuario;
        Scanner scanner = new Scanner(System.in);

        // Login do usuário
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
                System.out.println("");
            }
        }

        if(usuario instanceof Estudante) {
            tipoUsuario = 1;
        } else if (usuario instanceof Professor) {
            tipoUsuario = 2;
        } else {
            tipoUsuario = 3;
        }

        // Sabendo o tipo de usuário
        switch(tipoUsuario) {
            case 1:
                while(menuPrincipal) {
                	mv.header();
                	mv.textCenter("Bem-vindo | " + usuario.getNomeUsuario());
                	mv.border();
                	mv.text("1 - Consultar Estudante");
                	mv.text("2 - Consultar Turma");
                	mv.text("3 - Consultar Presença");
                	mv.text("4 - Consultar Chat");
                	mv.text("5 - Enviar Mensagem");
                	mv.text("6 - Consultar Mensagem");
                	mv.text("9 - Voltar");
                	mv.textBox("0 - Encerrar Programa");
                	opcaoPrincipal = mv.inputOpcao();
                	mv.borderln();

                    EstudanteServiceInterface estudante = new EstudanteService();
                    switch(opcaoPrincipal) {
                        case 1:
                            try {
                                Estudante consultaEstudante = estudante.consultarEstudante(usuario.getIdUsuario());

                                mv.header();
                                mv.textCenter("Bem-Vindo | Estudante");
                                mv.border();
                                mv.text("Nome do Estudante: " + consultaEstudante.getNomeUsuario());
                                mv.text("E-mail do Estudante: " + consultaEstudante.getEmailUsuario());
                                mv.borderln();

                            } catch (DBUnavailable e) {
                                mv.textBox(e.getMessage());
                            }
                            break;
                        case 2:
                            try {
                            	Turma consultaTurma = estudante.consultarTurma(1);
                            	
                            	mv.header();
                                mv.textCenter("Bem-Vindo | Estudante");
                                mv.border();
                                mv.text("ID: " + consultaTurma.getIdTurma());
                                mv.text("Nome da Turma:" + consultaTurma.getNomeDisciplina());
                                mv.text("Capacidade de Estudantes: " + consultaTurma.getCapacidadeAlunos());
                                mv.borderln();
                            } catch(UserNotFoundException | DBUnavailable e) {
                            	mv.textBox(e.getMessage());
                            }
                            break;
                        case 3:
                        	try {
	                            Presenca consultaPresenca = estudante.consultarPresenca(usuario.getIdUsuario());
	                            
	                            mv.header();
	                            mv.textCenter("Bem-Vindo | Estudante");
	                            mv.border();
	                            mv.text("Data: " + consultaPresenca.getData());
	                            mv.text("ID do Estudante: " + consultaPresenca.getIdAluno());
	                            mv.text("ID da Turma: " + consultaPresenca.getIdTurma());
	                            mv.text("ID do Professor: " + consultaPresenca.getIdProfessor());
	                            mv.borderln();
                        	} catch (NullPointerException e) {
                        		mv.textBox(e.getMessage());
                        	}
	                        break;
                        case 4:
                            System.out.println("Consultou Chat");
                            break;
                        case 5:
                            System.out.println("Enviou Mensagem");
                            break;
                        case 6:
                            System.out.println("Consultou Mensagem");
                            break;
                        case 0:
                            menuPrincipal = false;
                            break;
                        default:
                            mv.text("Valor inválido");
                    }

                }

                break;
            case 2:
                while(menuPrincipal) {
                	mv.header();
                	mv.textCenter("Bem-vindo | " + usuario.getNomeUsuario());
                	mv.border();
                	mv.text("1 - Consultar Funcionario");
                	mv.text("2 - Consultar Turma");
                	mv.text("3 - Presença");
                	mv.text("4 - Chat");
                	mv.text("5 - Enviar Mensagem");
                	mv.text("6 - Consultar Mensagem");
                	mv.text("9 - Voltar");
                	mv.textBox("0 - Encerrar Programa");
                	opcaoPrincipal = mv.inputOpcao();
                	mv.borderln();
                	                 
                    switch(opcaoPrincipal) {
                        case 1:
                            System.out.println("Consultou Estudante");
//					estudante.consultarEstudante();
                            break;
                        case 2:
                            System.out.println("Consultou Turma");
//					estudante.consultarTurma();
                            break;
                        case 3:
                            while(menuSecundario) {
                            	mv.header();
                            	mv.textCenter("Bem-vindo | " + usuario.getNomeUsuario());
                            	mv.border();
                            	mv.text("1 - Inserir Presença");
                            	mv.text("2 - Consultar Presença");
                            	mv.text("3 - Alterar Presença");
                            	mv.text("9 - Voltar");
                            	mv.textBox("0 - Encerrar Programa");
                            	opcaoSecundaria = mv.inputOpcao();
                            	mv.borderln();
                            	
                                switch(opcaoSecundaria) {
                                    case 1:
                                        System.out.println("Inseriu Presença");
                                        break;
                                    case 2:
                                        System.out.println("Consultou Presença");
                                        break;
                                    case 3:
                                        System.out.println("Alterou Presença");
                                        break;
                                    case 9:
                                        menuSecundario = false;
                                        break;
                                    case 0: 
                                    	menuPrincipal = false;
                                    	menuSecundario = false;
                                    	break;
                                    default:
                                        mv.text("Valor inválido");
                                        mv.borderln();
                                }
                            }
                            menuSecundario = true;
                            break;
                        case 4:
                            while(menuSecundario) {
                            	mv.header();
                            	mv.textCenter("Bem-vindo | " + usuario.getNomeUsuario());
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
                                        mv.text("Valor inválido");
                                        mv.borderln();
                                }
                            }
                            menuSecundario = true;
                            break;
                        case 5:
                        	mv.text("Funcionalidade não implementada");
                        	mv.borderln();                          
                            break;
                        case 6:
                        	mv.text("Funcionalidade não implementada");
                        	mv.borderln();
                            break;
                        case 0:
                            menuPrincipal = false;
                            break;
                        default:
                        	mv.text("Valor inválido");
                            mv.borderln();
                    }
                }
                break;
            case 3:
                while(menuPrincipal) {
                    System.out.println("+-------------------------------------------------------------+");
                    System.out.println("|        SiGest - Sistema de Gerenciamento Estudantil         |");
                    System.out.println("+-------------------------------------------------------------+");
                    System.out.println("|                 Bem-Vindo | Gestor                          |");
                    System.out.println("+-------------------------------------------------------------+");
                    System.out.println("| 1 - Estudante                                               |");
                    System.out.println("| 2 - Turma                                                   |");
                    System.out.println("| 3 - Gestor                                                  |");
                    System.out.println("| 4 - Chat                                                    |");
                    System.out.println("| 5 - Mensagem                                                |");
                    System.out.println("| 6 - Turma                                                   |");
                    System.out.println("| 7 - Presença                                                |");
                    System.out.println("+-------------------------------------------------------------+");
                    System.out.println("| 0 - Encerrar Programa                                       |");
                    System.out.println("+-------------------------------------------------------------+");
                    System.out.print("| Sua opção: ");
                    opcaoPrincipal = scanner.nextInt();
                    System.out.println("+-------------------------------------------------------------+\n");
                    switch(opcaoPrincipal) {
                        case 1:
                            while(menuSecundario) {
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("|        SiGest - Sistema de Gerenciamento Estudantil         |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("|                         Estudante                            |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("| 1 - Inserir Estudante                                       |");
                                System.out.println("| 2 - Consultar Estudante                                     |");
                                System.out.println("| 3 - Alterar Estudante                                       |");
                                System.out.println("| 4 - Remover Estudante                                       |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("| 9 - Voltar                                                  |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.print("| Sua opção: ");
                                opcaoSecundaria = scanner.nextInt();
                                System.out.println("+-------------------------------------------------------------+\n");
                                switch(opcaoSecundaria) {
                                    case 1:
                                        System.out.println("Inseriu Estudante");
                                        break;
                                    case 2:
                                        System.out.println("Consultou Estudante");
                                        break;
                                    case 3:
                                        System.out.println("Alterou Estudante");
                                        break;
                                    case 4:
                                        System.out.println("Removeu Estudante");
                                        break;
                                    case 9:
                                        menuSecundario = false;
                                        break;
                                    default:
                                        System.out.println("| Valor inválido                                              |");
                                        System.out.print("| Sua opção: ");
                                        opcaoSecundaria = scanner.nextInt();
                                }
                            }
                            menuSecundario = true;
                            break;
                        case 2:
                            while(menuSecundario) {
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("|        SiGest - Sistema de Gerenciamento Estudantil         |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("|                       Funcionario                           |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("| 1 - Inserir Funcionario                                     |");
                                System.out.println("| 2 - Consultar Funcionario                                   |");
                                System.out.println("| 3 - Alterar Funcionario                                     |");
                                System.out.println("| 4 - Remover Funcionario                                     |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("| 9 - Voltar                                                  |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.print("| Sua opção: ");
                                opcaoSecundaria = scanner.nextInt();
                                System.out.println("+-------------------------------------------------------------+\n");
                                switch(opcaoSecundaria) {
                                    case 1:
                                        System.out.println("Inseriu Funcionario");
                                        break;
                                    case 2:
                                        System.out.println("Consultou Funcionario");
                                        break;
                                    case 3:
                                        System.out.println("Alterou Funcionario");
                                        break;
                                    case 4:
                                        System.out.println("Removeu Funcionario");
                                        break;
                                    case 9:
                                        menuSecundario = false;
                                        break;
                                    default:
                                        System.out.println("| Valor inválido                                              |");
                                        System.out.print("| Sua opção: ");
                                        opcaoSecundaria = scanner.nextInt();
                                }
                            }
                            menuSecundario = true;
                            break;
                        case 3:
                            while(menuSecundario) {
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("|        SiGest - Sistema de Gerenciamento Estudantil         |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("|                           Chat                              |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("| 1 - Inserir Chat                                            |");
                                System.out.println("| 2 - Consultar Chat                                          |");
                                System.out.println("| 3 - Alterar Chat                                            |");
                                System.out.println("| 4 - Remover Chat                                            |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("| 9 - Voltar                                                  |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.print("| Sua opção: ");
                                opcaoSecundaria = scanner.nextInt();
                                System.out.println("+-------------------------------------------------------------+\n");
                                switch(opcaoSecundaria) {
                                    case 1:
                                        System.out.println("Inseriu Chat");
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
                                    default:
                                        System.out.println("| Valor inválido                                              |");
                                        System.out.print("| Sua opção: ");
                                        opcaoSecundaria = scanner.nextInt();
                                }
                            }
                            menuSecundario = true;
                            break;
                        case 4:
                            while(menuSecundario) {
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("|        SiGest - Sistema de Gerenciamento Estudantil         |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("|                         Gestor                              |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("| 1 - Inserir Gestor                                          |");
                                System.out.println("| 2 - Consultar Gestor                                        |");
                                System.out.println("| 3 - Alterar Gestor                                          |");
                                System.out.println("| 4 - Remover Gestor                                          |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("| 9 - Voltar                                                  |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.print("| Sua opção: ");
                                opcaoSecundaria = scanner.nextInt();
                                System.out.println("+-------------------------------------------------------------+\n");
                                switch(opcaoSecundaria) {
                                    case 1:
                                        System.out.println("Inseriu Gestor");
                                        break;
                                    case 2:
                                        System.out.println("Consultou Gestor");
                                        break;
                                    case 3:
                                        System.out.println("Alterou Gestor");
                                        break;
                                    case 4:
                                        System.out.println("Removeu Gestor");
                                        break;
                                    case 9:
                                        menuSecundario = false;
                                        break;
                                    default:
                                        System.out.println("| Valor inválido                                              |");
                                        System.out.print("| Sua opção: ");
                                        opcaoSecundaria = scanner.nextInt();
                                }
                            }
                            menuSecundario = true;
                            break;
                        case 5:
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
                                System.out.print("| Sua opção: ");
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
                                        System.out.println("| Valor inválido                                              |");
                                        System.out.print("| Sua opção: ");
                                        opcaoSecundaria = scanner.nextInt();
                                }
                            }
                            menuSecundario = true;
                            break;
                        case 6:
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
                                System.out.print("| Sua opção: ");
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
                                        System.out.println("| Valor inválido                                              |");
                                        System.out.print("| Sua opção: ");
                                        opcaoSecundaria = scanner.nextInt();
                                }
                            }
                            menuSecundario = true;
                            break;
                        case 7:
                            while(menuSecundario) {
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("|        SiGest - Sistema de Gerenciamento Estudantil         |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("|                         Presença                            |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("| 1 - Inserir Presença                                        |");
                                System.out.println("| 2 - Consultar Presença                                      |");
                                System.out.println("| 3 - Alterar Presença                                        |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("| 9 - Voltar                                                  |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.print("| Sua opção: ");
                                opcaoSecundaria = scanner.nextInt();
                                System.out.println("+-------------------------------------------------------------+\n");
                                switch(opcaoSecundaria) {
                                    case 1:
                                        System.out.println("Inseriu Presença");
                                        break;
                                    case 2:
                                        System.out.println("Consultou Presença");
                                        break;
                                    case 3:
                                        System.out.println("Alterou Presença");
                                        break;
                                    case 9:
                                        menuSecundario = false;
                                        break;
                                    default:
                                        System.out.println("| Valor inválido                                              |");
                                        System.out.print("| Sua opção: ");
                                        opcaoSecundaria = scanner.nextInt();
                                }
                            }
                            menuSecundario = true;
                            break;
                        case 0:
                            menuPrincipal = false;
                            break;
                        default:
                            System.out.println("| Valor inválido                                              |");
                            System.out.print("| Sua opção: ");
                            opcaoPrincipal = scanner.nextInt();
                    }
                }
                break;
        }

        scanner.close();
    }

}