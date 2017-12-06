import scala.concurrent.Future

class callback {

  def callback(code: String)(implicit req: Request[_]): Future[Option[AccountEntity]] = {
    val account: AccountEntity = SessionUtil.getAccount
    ACCESS_TOKEN(code, req, env).flatMap {
      case Some(oauth: Oauth) =>
        new Facebook(oauth.accessToken).getUser().flatMap {
          case Some(response) =>
            if (account.isLogin) {
              val newUser = account.user.get.copy(facebookId = Some(response.id), facebookAccessToken = Some(oauth.accessToken))
              update(newUser).flatMap { _ =>
                SessionUtil.setAccount(account.session, account.copy(user = Some(newUser)))
                Future successful Some(account)
              }
            } else {
              getByFacebookId(response.id).flatMap {
                case Some(user) =>
                  val newAccount = SessionUtil.refreshSession(account.copy(user = Some(user)))
                  Future successful Some(newAccount)
                case None => Future successful None
              }
            }
          case None => Future successful None
        }
      case _ => Future successful None
    }
  }

}
