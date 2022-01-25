package edu.ohdm.editor.model;

public class Eintrag {
    private String name;
    private boolean checked;

    public Eintrag()
    {
        this("empty",false);
    }

    public Eintrag(String name, boolean checked)
    {
        this.name = name;
        this.checked = checked;
    }

    public String getName(){return this.name;}
    public boolean getChecked(){return this.checked;}

    public void setName(String name){this.name = name;}
    public void setChecked(boolean checked){this.checked = checked;}
}
