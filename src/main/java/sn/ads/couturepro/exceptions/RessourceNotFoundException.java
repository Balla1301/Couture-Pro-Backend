package sn.ads.couturepro.exceptions;

public class RessourceNotFoundException extends CoutureProException{
    public RessourceNotFoundException(String resource, Long id) {
        super(resource + " non trouvé avec l'id : " + id);
    }
}
