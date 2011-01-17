package com.dynamo.cr.ddfeditor.wizards;


public class CollectionNewWizard extends AbstractNewDdfWizard {
    @Override
    public String getTitle() {
        return "Collection binding file";
    }

    @Override
    public String getDescription() {
        return "This wizard creates a new collection file.";
    }

    @Override
    public String getExtension() {
        return "collection";
    }

}
