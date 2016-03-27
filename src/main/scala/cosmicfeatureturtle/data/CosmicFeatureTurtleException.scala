package cosmicfeatureturtle.data

class CosmicFeatureTurtleException(m: String) extends Exception(m) {
  val message = m

  def this() {
    this("An error occurred.")
  }
}
