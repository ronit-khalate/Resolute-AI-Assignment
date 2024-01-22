package com.ronit.resoluteaiassignment.feature_auth.ui.signup.event

sealed class VerificationEvent(val digit: String) {

    class FirstDigitEntered(digit: String) : VerificationEvent(digit)
    class SecondDigitEntered(digit: String): VerificationEvent(digit)
    class ThirdDigitEntered(digit: String) : VerificationEvent(digit)
    class FourthDigitEntered(digit: String) : VerificationEvent(digit)
    class FifthDigitEntered(digit: String) : VerificationEvent(digit)
    class SixthDigitEntered(digit: String) : VerificationEvent(digit)

    class Verify(val signUp:()->Unit):VerificationEvent("")
}