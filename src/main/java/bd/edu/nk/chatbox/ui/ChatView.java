package bd.edu.nk.chatbox.ui;

import bd.edu.nk.chatbox.model.Message;
import bd.edu.nk.chatbox.model.User;
import bd.edu.nk.chatbox.repository.MessageRepository;
import bd.edu.nk.chatbox.service.MessageService;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;


@Route("chat")
@Theme(value = Lumo.class , variant = Lumo.DARK)
public class ChatView extends VerticalLayout {

    private User sender;
    private Grid<Message> grid;
    private MessageService messageService;
    private Binder<Message> messageBinder;

    public ChatView( MessageService messageService) {

        this.messageService = messageService;
        sender = ComponentUtil.getData(UI.getCurrent(), User.class);
        System.out.println(sender);
        messageBinder = new Binder<>();
        grid = new Grid<>();

        grid
                .addColumn(Message::getSender)
                .setWidth("150px")
                .setFlexGrow(0)
                .setHeader("Sender");
        grid
                .addColumn(Message::getMsg)
                .setFlexGrow(1)
                .setHeader("Message");

        grid.setItems(messageService.getMessages());

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSizeFull();
        horizontalLayout.setSpacing(true);

        TextField textField = new TextField("","Type message");
        textField.setSizeFull();

        TextField nameField = new TextField();
        nameField.setValue(sender.getName());

        messageBinder
                .forField(nameField)
                .bind(Message::getSender,Message::setSender);

        messageBinder
                .forField(textField)
                .asRequired()
                .bind(Message::getMsg,Message::setMsg);


        Button send = new Button("send");
        send.setSizeFull();
        send.setWidth("100px");
        send.setIcon(VaadinIcon.ARROW_CIRCLE_RIGHT.create());
        send.addClickListener(e ->{
           Message msg = new Message();
            try {
                messageBinder.writeBean(msg);
                System.out.println(msg);
                messageService.saveMessage(msg);
                grid.setItems(messageService.getMessages());
                textField.setValue("");
            } catch (ValidationException ex) {
                ex.printStackTrace();
            }
        });



        horizontalLayout.add(textField,send);
        add(grid,horizontalLayout);
    }
}
