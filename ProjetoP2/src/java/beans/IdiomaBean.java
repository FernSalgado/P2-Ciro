package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

@Named(value = "idioma")
@RequestScoped
public class IdiomaBean implements Serializable {
    private String login;
    private String senha;
    private Locale localizacao;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    private static final Locale[] COUNTRIES
            = {Locale.forLanguageTag("pt-br"), Locale.ENGLISH};

    public IdiomaBean() { }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Locale getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Locale localizacao) {
        this.localizacao = localizacao;
    }

    public void mudouIdioma(ValueChangeEvent event) {
        FacesContext.getCurrentInstance().getViewRoot().
                setLocale((Locale) event.getNewValue());
    }

    public void toUpper(ValueChangeEvent event) {
        login = event.getNewValue().toString().toUpperCase();
        System.out.println(login);
    }

    public List<SelectItem> getIdiomasSuportados() {
        List<SelectItem> idiomas = new ArrayList<>();
        for (Locale loc : COUNTRIES) {
            idiomas.add(new SelectItem(loc,
                    loc.getDisplayLanguage()));
        }
        return idiomas;
    }
    
    public String Logar(){
        return "/home.xhtml";
    }

}
