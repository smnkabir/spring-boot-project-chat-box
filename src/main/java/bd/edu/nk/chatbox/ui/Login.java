package bd.edu.nk.chatbox.ui;



import bd.edu.nk.chatbox.model.User;
import bd.edu.nk.chatbox.service.MessageService;
import bd.edu.nk.chatbox.service.UserService;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;


@Route(value = "")
@Theme(value = Lumo.class , variant = Lumo.DARK)
public class Login extends VerticalLayout {

    private ProgressBar progressBar = new ProgressBar();
    private VerticalLayout verticalLayout ;
    private Binder<User> userBinder;
    private UserService userService;
    private  MessageService messageService;

    public Login(UserService userService ,  MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
        userBinder = new Binder<>();

        verticalLayout = new VerticalLayout();
        add(verticalLayout);

        verticalLayout.setAlignItems(Alignment.CENTER);
        progressBar.setVisible(false);
        verticalLayout.add(progressBar);

        TextField userNameField = new TextField("User Name","Enter your user name");

        userNameField.addFocusListener( e -> {
            progressBar.setIndeterminate(false);
            progressBar.setVisible(false);
        });

        PasswordField passwordField = new PasswordField("Password","Enter your password");
        passwordField.addThemeVariants(TextFieldVariant.LUMO_SMALL);

        userBinder
                .forField(userNameField)
                .asRequired()
                .bind(User::getName,User::setName);
        userBinder
                .forField(passwordField)
                .asRequired()
                .bind(User::getPassword,User::setPassword);

        Button login = new Button("LogIn");
        login.addClickListener(e->{
            progressBar.setIndeterminate(true);
            progressBar.setVisible(true);
            User user = new User();
            try {
                userBinder.writeBean(user);
                System.out.println(user);
                if(userService.isValid(user)){

                    ComponentUtil.setData(UI.getCurrent(),User.class,user);
                    UI.getCurrent().navigate("chat" );
                }
                else Notification.show("Invalid Login");

            } catch (ValidationException ex) {
                ex.printStackTrace();
                Notification.show("Log in failed");
            }

        });

        login.addThemeVariants(ButtonVariant.LUMO_SMALL);
        setSpacing(true);
        verticalLayout.add(userNameField,passwordField,login);
    }
}
