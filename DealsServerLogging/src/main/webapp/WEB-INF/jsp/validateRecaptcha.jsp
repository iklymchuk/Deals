<%@ page import="net.tanesha.recaptcha.ReCaptchaImpl,net.tanesha.recaptcha.ReCaptchaResponse" %><%
ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
reCaptcha.setPrivateKey("your_private_key");
String remoteip = request.getParameter("remoteip");
String challenge = request.getParameter("challenge");
String res = request.getParameter("response");
ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteip, challenge, res);
if (reCaptchaResponse.isValid()) {
out.print("true");
} else {
out.print("false");
}
%> 