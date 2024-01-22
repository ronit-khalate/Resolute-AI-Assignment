package com.ronit.resoluteaiassignment.feature_auth.ui.signup.state

data class VerificationState(
        val firstDigit:String="",
        val secondDigit:String="",
        val thirdDigit:String="",
        val fourthDigit:String="",
        val fifthDigit:String="",
        val sixthDigit:String="",
){

        fun getTypedOtp():String{

                return buildString {
                        append(firstDigit.trim())
                        append(secondDigit.trim())
                        append(thirdDigit.trim())
                        append(fourthDigit.trim())
                        append(fifthDigit.trim())
                        append(sixthDigit.trim())

                }
        }
}