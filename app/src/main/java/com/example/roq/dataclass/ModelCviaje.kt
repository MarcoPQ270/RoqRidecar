package com.example.roq.dataclass
class ModelCviaje (id:String,municipio:String, horas:String, nota:String, nocontrol : String, nombrec : String) {
    var id:String=""
    var municip: String =""
    var hors: String=""
    var nomc: String =""
    var nocont: String=""
    var note: String=""

    init{
        this.id=id
        this.municip=municipio
        this.hors=horas
        this.nomc = nombrec
        this.nocont = nocontrol
        this.note=nota
    }
}