package com.dynamo.cr.web2.client.ui;

import com.dynamo.cr.web2.client.AsciiDocUtil;
import com.dynamo.cr.web2.shared.ClientUtil;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class OpenIDView extends Composite implements IAsciiDocView {

    public interface Presenter {
        void register(String registrationKey);
    }

    private static final Binder binder = GWT.create(Binder.class);
    @UiField DeckPanel deckPanel;
    @UiField InlineLabel emailLabel;
    @UiField InlineLabel firstNameLabel;
    @UiField InlineLabel lastNameLabel;
    @UiField TextBox registrationKey;
    @UiField Button registerButton;
    @UiField HTMLPanel registrationKeyBox;
    @UiField HTMLPanel eula;
    @UiField Image loader;

    private OpenIDView.Presenter listener;

    interface Binder extends UiBinder<Widget, OpenIDView> {
    }

    public OpenIDView() {
        initWidget(binder.createAndBindUi(this));
    }

    public void showConfirmRegistrationPanel() {
        deckPanel.setVisible(true);
        deckPanel.showWidget(0);
    }

    public void showLoginSuccessfulPanel() {
        deckPanel.setVisible(true);
        deckPanel.showWidget(1);
    }

    public void hideDeckPanel() {
       deckPanel.setVisible(false);
    }

    public void setPresenter(OpenIDView.Presenter listener) {
        this.listener = listener;
        hideDeckPanel();
        firstNameLabel.setText("");
        lastNameLabel.setText("");
        emailLabel.setText("");
        boolean dev = ClientUtil.isDev();
        // For testing
        registrationKeyBox.setVisible(dev);
        if (dev) {
            String registrationKey = Cookies.getCookie("registration_key");
            if (registrationKey != null) {
                this.registrationKey.setText(registrationKey);
            } else {
                this.registrationKey.setText("");
            }
        }
    }

    public void setConfirmRegistrationData(String firstName, String lastName, String email) {
        firstNameLabel.setText(firstName);
        lastNameLabel.setText(lastName);
        emailLabel.setText(email);
    }

    @UiHandler("registerButton")
    void onRegisterButtonClick(ClickEvent event) {
        listener.register(registrationKey.getText());
    }

    @Override
    public void setText(String html) {
        /*
         * Extract document payload, ie within <body></body>. We must
         * remove the inline css. The inline css disturb the default one. See ascii.css
         * for more information.
         */
        eula.clear();
        loader.setVisible(false);
        eula.add(AsciiDocUtil.extractBody(html));
    }

    @Override
    public void setLoading(boolean loading) {
        loader.setVisible(loading);
    }

    @Override
    public void clear() {
        eula.clear();
    }
}
