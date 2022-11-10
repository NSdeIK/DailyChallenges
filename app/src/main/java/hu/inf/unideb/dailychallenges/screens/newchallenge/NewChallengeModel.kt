package hu.inf.unideb.dailychallenges.screens.newchallenge

class NewChallengeModel (private var type: String, private var image: Int) {
    fun getChallenge_type(): String {
        return type
    }

    fun setChallenge_type(type: String) {
        this.type = type
    }

    fun getChallenge_image(): Int {
        return image
    }

    fun setChallenge_image(image: Int) {
        this.image = image
    }
}