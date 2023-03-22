package cards;

import gui.App;

import javax.swing.*;

public abstract class NISTCard extends Card{
    NISTCard(App app){
        super(app, app.textFieldExtractorInput, app.textFieldExtractorOutput);
    }
    @Override
    public void nextCard(){
        app.displayNextNISTCard(n);
        app.nistReady[n] = false;
    }
}
