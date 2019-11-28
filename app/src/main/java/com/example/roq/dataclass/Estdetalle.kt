package com.example.roq.dataclass

class Estdetalle (controlest:String,nombreest:String, semestreest:String, carreraest:String, nocontrolchof:String) {
    var nocon:String=""
    var nombree: String =""
    var semes: String=""
    var carr: String =""
    var controlchof:String=""

    init{
        this.nocon=controlest
        this.nombree=nombreest
        this.semes=semestreest
        this.carr = carreraest
        this.controlchof=nocontrolchof
    }
}