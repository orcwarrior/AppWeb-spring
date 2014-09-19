package org.springframework.samples.webflow.rest.request;

import org.springframework.samples.webflow.rest.request.concrete.RESTRequestLogin;
import org.springframework.samples.webflow.rest.request.headers.RESTOperation;

/**
 * Created by orcwarrior on 2014-09-17.
 */
public class RESTRequestFactory {
    /* Simply making concrete request from generic */
    public static RESTRequest buildConcreteRequest(RESTRequestGeneric requestGeneric) {
        switch (requestGeneric.getHeaders().getOperation())
        {
            case login:
                return new RESTRequestLogin(requestGeneric.getHeaders(), requestGeneric.getContent());
            case logout:
                break;
            case register:
                break;
            case getRoles:
                break;
            case getArticles:
                break;
            case getArticleFull:
                break;
            case getArticleComments:
                break;
            case favoriteArticle:
                break;
            case editArticle:
                break;
            case deleteArticle:
                break;
            case createArticle:
                break;
            case addComment:
                break;
            case deleteComment:
                break;
        }
        throw new EnumConstantNotPresentException(RESTOperation.class, "Niedozwolona operacja "+requestGeneric.getHeaders().getOperation().name());
    }
}
