package cosmicfeatureturtle.models

object Models {
  trait CredentialRequest {
    val idUser: Int
    val apiKey: String
  }

  case class User(idUser: Int, username: String)
  case class FeatureSummary(user: User, idFeature: Int, title: String, idea: String, commentCount: Int, upvoteCount: Int, downvoteCount: Int)

  case class FeatureComment(user: User, idComment: Int, body: String)
  case class FeatureVote(user: User, idVote: Int, upvote: Boolean)
  case class Feature(user: User, idFeature: Int, title: String, idea: String, comments: List[FeatureComment], votes: List[FeatureVote])

  case class ErrorMessage(error: String)
  case class CreateUserRequest(username: String, password: String, repassword: String)
  case class CreateUserResponse(idUser: Int, username: String, apiKey: String)
  case class LoginUserRequest(username: String, password: String)
  case class LoginUserResponse(idUser: Int, username: String, apiKey: String)
  case class CredentialResponse(idUser: Int, username: String)
  case class CreateFeatureRequest(idUser: Int, apiKey: String, title: String, idea: String) extends CredentialRequest
  case class CreateFeatureResponse(idFeature: Int, title: String, idea: String)
  case class DeleteFeatureRequest(idUser: Int, apiKey: String, idFeature: Int) extends CredentialRequest
  case class DeleteFeatureResponse(response: String)
  case class EditFeatureRequest(idUser: Int, apiKey: String, idFeature: Int, title: String, idea: String) extends CredentialRequest
  case class EditFeatureResponse(response: String)
  case class CreateVoteRequest(idUser: Int, apiKey: String, idFeature: Int, upvote: Boolean) extends CredentialRequest
  case class CreateVoteResponse(idVote: Int)
  case class DeleteVoteRequest(idUser: Int, apiKey: String, idVote: Int) extends CredentialRequest
  case class DeleteVoteResponse(response: String)
  case class EditVoteRequest(idUser: Int, apiKey: String, idVote: Int, upvote: Boolean) extends CredentialRequest
  case class EditVoteResponse(response: String)
  case class CreateCommentRequest(idUser: Int, apiKey: String, idFeature: Int, body: String) extends CredentialRequest
  case class CreateCommentResponse(idComment: Int, body: String)
  case class EditCommentRequest(idUser: Int, apiKey: String, idComment: Int, body: String) extends CredentialRequest
  case class EditCommentResponse(response: String)
  case class DeleteCommentRequest(idUser: Int, apiKey: String, idComment: Int) extends CredentialRequest
  case class DeleteCommentResponse(response: String)
}
