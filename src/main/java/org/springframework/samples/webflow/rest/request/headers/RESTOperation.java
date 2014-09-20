package org.springframework.samples.webflow.rest.request.headers;

import com.google.gson.annotations.SerializedName;

/**
 * Created by orcwarrior on 2014-09-15.
 */
public enum RESTOperation {
    /* REQUESTS */
     /* authorization */
    @SerializedName("RESTOperation.login")
    login,
    @SerializedName("RESTOperation.logout")
    logout,
    @SerializedName("RESTOperation.register")
    register,
    @SerializedName("RESTOperation.getRoles")
    getRoles,
    /* article basic */
    @SerializedName("RESTOperation.getArticles")
    getArticles,
    @SerializedName("RESTOperation.getArticleFull")
    getArticleFull,
    @SerializedName("RESTOperation.getArticleComments")
    getArticleComments,
    @SerializedName("RESTOperation.favoriteArticle")
    favoriteArticle,
    /* article modifications */
    @SerializedName("RESTOperation.editArticle")
    editArticle,
    @SerializedName("RESTOperation.deleteArticle")
    deleteArticle,
    @SerializedName("RESTOperation.createArticle")
    createArticle,
    /* comments */
    @SerializedName("RESTOperation.addComment")
    addComment,
    @SerializedName("RESTOperation.deleteComment")
    deleteComment

}
