package shop.kokodo.calculateservice;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.restdocs.cli.CliDocumentation.curlRequest;
import static org.springframework.restdocs.http.HttpDocumentation.httpRequest;
import static org.springframework.restdocs.http.HttpDocumentation.httpResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

/**
 * packageName    : shop.kokodo.calculateservice
 * fileName       : DocumentConfiguration
 * author         : namhyeop
 * date           : 2022/10/26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/26        namhyeop       최초 생성
 */

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public abstract class DocumentConfiguration extends AcceptanceTest {

    public RequestSpecification spec;

//    @BeforeEach
//    void setUpRestDocs(RestDocumentationContextProvider restDocumentation) {
//        this.spec = new RequestSpecBuilder()
//                .addFilter(
//                        documentationConfiguration(restDocumentation)
//                                .snippets()
//                                .withDefaults(
//                                        curlRequest(),
//                                        httpRequest(),
//                                        httpResponse(),
//                                        requestBody(),
//                                        responseBody()
//                                )
//                )
//                .build();
//    }
//
//    protected Snippet simpleReadPathParameterSnippet() {
//        return pathParameters(parameterWithName("id").description("아이디"));
//    }
//
//    protected Snippet simpleRequestParameterSnippet() {
//        return requestParameters(parameterWithName("name").description("이름"));
//    }
//
//    protected Snippet simpleRequestBodySnippet() {
//        return requestFields(
//                fieldWithPath("name")
//                        .type(JsonFieldType.STRING)
//                        .description("이름")
//
//        );
//    }
//
//    protected Snippet simpleResponseFieldsSnippet() {
//        return responseFields(
//                fieldWithPath("id")
//                        .type(JsonFieldType.NUMBER)
//                        .description("아이디"),
//                fieldWithPath("name")
//                        .type(JsonFieldType.STRING)
//                        .description("이름")
//        );
//    }


    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        this.spec = new RequestSpecBuilder()
                .addFilter(documentationConfiguration(restDocumentation)
                        .operationPreprocessors()
                        .withResponseDefaults(prettyPrint())
                        .withRequestDefaults(prettyPrint())
                )
                .build();
    }
}