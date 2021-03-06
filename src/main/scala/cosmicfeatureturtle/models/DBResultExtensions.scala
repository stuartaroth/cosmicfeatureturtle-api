package cosmicfeatureturtle.models

import scalikejdbc.WrappedResultSet
import cosmicfeatureturtle.models.Models._

object DBResultExtensions {
  implicit class DBResultExtensions(val rs: WrappedResultSet) extends AnyVal {
    def toCreateUserResponse = CreateUserResponse(rs.int("id_user"), rs.string("name"), rs.string("api_key"))

    def toLoginUserResponse = LoginUserResponse(rs.int("id_user"), rs.string("name"), rs.string("api_key"))

    def toCredentialResponse = CredentialResponse(rs.int("id_user"), rs.string("name"))

    def toCreateFeatureResponse = CreateFeatureResponse(rs.int("id_feature"), rs.string("title"), rs.string("idea"))

    def toFeatureForDeletion = FeatureForDeletion(rs.int("id_user"), rs.int("id_feature"))

    def toFeatureSummary = {
      FeatureSummary(
        User(rs.int("id_user"), rs.string("name")),
        rs.int("id_feature"),
        rs.string("title"),
        rs.string("idea"),
        rs.jodaDateTime("date_created"),
        rs.jodaDateTime("date_edited"),
        rs.intOpt("comment_count"),
        rs.intOpt("upvote_count"),
        rs.intOpt("downvote_count"))
    }

    def toFeatureComment = {
      FeatureComment(
        User(rs.int("id_user"), rs.string("name")),
        rs.int("id_comment"),
        rs.string("body"),
        rs.jodaDateTime("date_created"),
        rs.jodaDateTime("date_edited")
      )
    }

    def toFeatureVote = {
      FeatureVote(
        User(rs.int("id_user"), rs.string("name")),
        rs.int("id_vote"),
        rs.boolean("upvote")
      )
    }

    def toFeature(comments: List[FeatureComment], votes: List[FeatureVote]) = {
      Feature(
        User(rs.int("id_user"), rs.string("name")),
        rs.int("id_feature"),
        rs.string("title"),
        rs.string("idea"),
        rs.jodaDateTime("date_created"),
        rs.jodaDateTime("date_edited"),
        comments,
        votes
      )
    }

    def toCreateVoteResponse = CreateVoteResponse(rs.int("id_vote"))

    def toCreateCommentResponse = CreateCommentResponse(rs.int("id_comment"), rs.string("body"))
  }
}
