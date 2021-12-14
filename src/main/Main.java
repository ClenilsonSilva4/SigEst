package main;

import java.util.Scanner;

import entities.Estudante;
import entities.Professor;
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

public class Main {
    public static void main(String[] args) {
        LoginServiceInterface login = new LoginService();

        boolean menuPrincipal = true, menuSecundario = true;
        String email, senha;
        int tipoUsuario, opcaoPrincipal, opcaoSecundaria;

        Usuario usuario;
        Scanner scanner = new Scanner(System.in);

        // Login do usuário
        while(true) {
            System.out.println("+-------------------------------------------------------------+");
            System.out.println("|        SiGest - Sistema de Gerenciamento Estudantil         |");
            System.out.println("+-------------------------------------------------------------+");
            System.out.println("|                          LOGIN                              |");
            System.out.println("+-------------------------------------------------------------+");
            System.out.print("Digite seu e-mail: ");
            email = scanner.nextLine();
            System.out.print("Digite sua senha: ");
            senha = scanner.nextLine();

            try {
                usuario = login.checarLogin(email, senha);
                if(usuario != null) {
                    break;
                }
            } catch (UserNotFoundException | DBUnavailable | InvalidData e) {
                System.out.println(e.getMessage());
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
                    System.out.println("+-------------------------------------------------------------+");
                    System.out.println("|        SiGest - Sistema de Gerenciamento Estudantil         |");
                    System.out.println("+-------------------------------------------------------------+");
                    System.out.println("|                 Bem-Vindo | " + usuario.getNomeUsuario() + "                       |");
                    System.out.println("+-------------------------------------------------------------+");
                    System.out.println("| 1 - Consultar Estudante                                     |");
                    System.out.println("| 2 - Consultar Turma                                         |");
                    System.out.println("| 3 - Consultar Presença                                      |");
                    System.out.println("| 4 - Consultar Chat                                          |");
                    System.out.println("| 5 - Enviar Mensagem                                         |");
                    System.out.println("| 6 - Consultar Mensagem                                      |");
                    System.out.println("+-------------------------------------------------------------+");
                    System.out.println("| 0 - Encerrar Programa                                       |");
                    System.out.println("+-------------------------------------------------------------+");
                    System.out.print("| Sua opção: ");
                    opcaoPrincipal = scanner.nextInt();
                    System.out.println("+-------------------------------------------------------------+\n");

                    EstudanteServiceInterface estudante = new EstudanteService();
                    switch(opcaoPrincipal) {
                        case 1:
                            System.out.println("Consultou Estudante");
                            try {
                                Estudante consultaEstudante = estudante.consultarEstudante(usuario.getIdUsuario());

                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("|        SiGest - Sistema de Gerenciamento Estudantil         |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("|                 Bem-Vindo | Estudante                       |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("| Nome do Estudante: " + consultaEstudante.getNomeUsuario() + "                                |");
                                System.out.println("| E-mail do Estudante: " + consultaEstudante.getEmailUsuario() + "                    |");
                                System.out.println("+-------------------------------------------------------------+");

                            } catch (DBUnavailable e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 2:
                            System.out.println("Consultou Turma");
//					estudante.consultarTurma();
                            break;
                        case 3:
                            System.out.println("Consultou Presença");
//					estudante.consultarPresenca();
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
                            System.out.println("| Valor inválido                                              |");
                            System.out.print("| Sua opção: ");
                            opcaoPrincipal = scanner.nextInt();
                    }

                }

                break;
            case 2:
                while(menuPrincipal) {
                    System.out.println("+-------------------------------------------------------------+");
                    System.out.println("|        SiGest - Sistema de Gerenciamento Estudantil         |");
                    System.out.println("+-------------------------------------------------------------+");
                    System.out.println("|                 Bem-Vindo | Professor                       |");
                    System.out.println("+-------------------------------------------------------------+");
                    System.out.println("| 1 - Consultar Funcionario                                   |");
                    System.out.println("| 2 - Consultar Turma                                         |");
                    System.out.println("| 3 - Presença                                                |");
                    System.out.println("| 4 - Chat                                                    |");
                    System.out.println("| 5 - Enviar Mensagem                                         |");
                    System.out.println("| 6 - Consultar Mensagem                                      |");
                    System.out.println("+-------------------------------------------------------------+");
                    System.out.println("| 0 - Encerrar Programa                                       |");
                    System.out.println("+-------------------------------------------------------------+");
                    System.out.print("| Sua opção: ");
                    opcaoPrincipal = scanner.nextInt();
                    System.out.println("+-------------------------------------------------------------+\n");
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
                        case 4:
                            while(menuSecundario) {
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("|        SiGest - Sistema de Gerenciamento Estudantil         |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("|                           Chat                              |");
                                System.out.println("+-------------------------------------------------------------+");
                                System.out.println("| 1 - Criar Chat                                              |");
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
                                    default:
                                        System.out.println("| Valor inválido                                              |");
                                        System.out.print("| Sua opção: ");
                                        opcaoSecundaria = scanner.nextInt();
                                }
                            }
                            menuSecundario = true;
                            break;
                        case 5:
                            System.out.println("| Funcionalidade nço implementada                             |");
                            System.out.println("+-------------------------------------------------------------+\n");
                            break;
                        case 6:
                            System.out.println("| Funcionalidade nço implementada                             |");
                            System.out.println("+-------------------------------------------------------------+\n");
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