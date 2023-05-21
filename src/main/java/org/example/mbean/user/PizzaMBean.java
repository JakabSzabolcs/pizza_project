package org.example.mbean.user;

import jdk.jfr.Name;
import org.example.entity.Pizza;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class PizzaMBean implements Serializable
{
    private List<Pizza> list = new ArrayList<>();
    private Pizza selectedPizza = new Pizza();


}
