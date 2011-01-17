package com.dynamo.cr.ddfeditor.wizards;


public class CameraNewWizard extends AbstractNewDdfWizard {
    @Override
    public String getTitle() {
        return "Camera file";
    }

    @Override
    public String getDescription() {
        return "This wizard creates a new camera file.";
    }

    @Override
    public String getExtension() {
        return "camera";
    }
}
