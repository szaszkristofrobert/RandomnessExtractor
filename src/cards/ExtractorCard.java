package cards;


import gui.App;

import javax.swing.*;

public abstract class ExtractorCard extends Card{
    ExtractorCard(App app){
        super(app, app.textFieldExtractorInput, app.textFieldExtractorOutput);
    }
    @Override
    public void nextCard() {
        app.displayNextExtractorCard(n);
        app.extractorsReady[n-1] = false;
    }
}
