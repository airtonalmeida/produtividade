package br.com.ettec.produtividade.domain;

import java.sql.Time;
import java.util.Date;

/**
 * Created by usuario on 18/01/2017.
 */

public class ProdutividadePolicial {

    private Integer id; ////primary key da tabela do sqlite

    private Integer codigo;

    private String descricao;

    private String dataProdutividade;

    private String horaInicio;

    private String horaFim;

    private Integer operacaoPolicial;

    private Integer turno;

    private String latitude;

    private String longitude;

    private Integer numVtrOPM;

    private Integer numVtrOutrasOPM;

    private Integer numVtrOrgaoMunicipalTransito;

    private Integer numVtrDETRAN;

    private Integer numVtrSEFAZ;

    private Integer numVtrOutrosOrgaos;

    private Integer numEscoltasRealizadas;

    private Integer numEfetivoOpm;

    private Integer numEfetivoApoio;

    private Integer numEfetivoReforco;

    private Integer numPessoasAbordadas;

    private Integer numEstabelecimentosAbordados;

    private Integer numVeiculos4RodasAbordados;

    private Integer numVeiculos2RodasAbordados;

    private Integer numVeiculosTransporteColetivoAbordados;

    private Integer numPontosOnibusAbordados;

    private Integer numTaxiAbordados;

    private Integer numOcorrenciasEnvolvendoPM;

    private Integer numOcorrenciasEnvolvendoPCivil;

    private Integer numOcorrenciasEnvolvendoForcasArmadas;

    private Integer numPessoasConduzidasCP;

    private Integer numPresosFlagranteDelito;

    private Integer numAdolescentesApreendidos;

    private Integer numTCOLavrados;

    private Integer numAutoResistenciaLavrado;

    private Integer numMandadosPrisaoCumpridos;

    private Integer numArmasFogoApreendidas;

    private Integer numArmasBrancasApreendidas;

    private Integer numVeiculosRecuperados;

    private Integer numVeiculos4RodasAutuados;

    private Integer numVeiculos2RodasAutuados;

    private Integer numVeiculos4RodasApreendidos;

    private Integer numVeiculos2RodasApreendidos;

    private Integer numVeiculosTransporteColetivoApreendidos;

    private Integer numCNHApreendidas;

    private Integer numMaconhaApreendidaemKg;

    private Integer numCocainaApreendidaemGramas;

    private Integer numCrackApreendidaemGramas;

    private Integer numOutrasdrogasapreendidas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataProdutividade() {
        return dataProdutividade;
    }

    public void setDataProdutividade(String dataProdutividade) {
        this.dataProdutividade = dataProdutividade;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public Integer getOperacaoPolicial() {
        return operacaoPolicial;
    }

    public void setOperacaoPolicial(Integer operacaoPolicial) {
        this.operacaoPolicial = operacaoPolicial;
    }

    public Integer getTurno() {
        return turno;
    }

    public void setTurno(Integer turno) {
        this.turno = turno;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Integer getNumVtrOPM() {
        return numVtrOPM;
    }

    public void setNumVtrOPM(Integer numVtrOPM) {
        this.numVtrOPM = numVtrOPM;
    }

    public Integer getNumVtrOutrasOPM() {
        return numVtrOutrasOPM;
    }

    public void setNumVtrOutrasOPM(Integer numVtrOutrasOPM) {
        this.numVtrOutrasOPM = numVtrOutrasOPM;
    }

    public Integer getNumVtrOrgaoMunicipalTransito() {
        return numVtrOrgaoMunicipalTransito;
    }

    public void setNumVtrOrgaoMunicipalTransito(Integer numVtrOrgaoMunicipalTransito) {
        this.numVtrOrgaoMunicipalTransito = numVtrOrgaoMunicipalTransito;
    }

    public Integer getNumVtrDETRAN() {
        return numVtrDETRAN;
    }

    public void setNumVtrDETRAN(Integer numVtrDETRAN) {
        this.numVtrDETRAN = numVtrDETRAN;
    }

    public Integer getNumVtrSEFAZ() {
        return numVtrSEFAZ;
    }

    public void setNumVtrSEFAZ(Integer numVtrSEFAZ) {
        this.numVtrSEFAZ = numVtrSEFAZ;
    }

    public Integer getNumVtrOutrosOrgaos() {
        return numVtrOutrosOrgaos;
    }

    public void setNumVtrOutrosOrgaos(Integer numVtrOutrosOrgaos) {
        this.numVtrOutrosOrgaos = numVtrOutrosOrgaos;
    }

    public Integer getNumEscoltasRealizadas() {
        return numEscoltasRealizadas;
    }

    public void setNumEscoltasRealizadas(Integer numEscoltasRealizadas) {
        this.numEscoltasRealizadas = numEscoltasRealizadas;
    }

    public Integer getNumEfetivoOpm() {
        return numEfetivoOpm;
    }

    public void setNumEfetivoOpm(Integer numEfetivoOpm) {
        this.numEfetivoOpm = numEfetivoOpm;
    }

    public Integer getNumEfetivoApoio() {
        return numEfetivoApoio;
    }

    public void setNumEfetivoApoio(Integer numEfetivoApoio) {
        this.numEfetivoApoio = numEfetivoApoio;
    }

    public Integer getNumEfetivoReforco() {
        return numEfetivoReforco;
    }

    public void setNumEfetivoReforco(Integer numEfetivoReforco) {
        this.numEfetivoReforco = numEfetivoReforco;
    }

    public Integer getNumPessoasAbordadas() {
        return numPessoasAbordadas;
    }

    public void setNumPessoasAbordadas(Integer numPessoasAbordadas) {
        this.numPessoasAbordadas = numPessoasAbordadas;
    }

    public Integer getNumEstabelecimentosAbordados() {
        return numEstabelecimentosAbordados;
    }

    public void setNumEstabelecimentosAbordados(Integer numEstabelecimentosAbordados) {
        this.numEstabelecimentosAbordados = numEstabelecimentosAbordados;
    }

    public Integer getNumVeiculos4RodasAbordados() {
        return numVeiculos4RodasAbordados;
    }

    public void setNumVeiculos4RodasAbordados(Integer numVeiculos4RodasAbordados) {
        this.numVeiculos4RodasAbordados = numVeiculos4RodasAbordados;
    }

    public Integer getNumVeiculos2RodasAbordados() {
        return numVeiculos2RodasAbordados;
    }

    public void setNumVeiculos2RodasAbordados(Integer numVeiculos2RodasAbordados) {
        this.numVeiculos2RodasAbordados = numVeiculos2RodasAbordados;
    }

    public Integer getNumVeiculosTransporteColetivoAbordados() {
        return numVeiculosTransporteColetivoAbordados;
    }

    public void setNumVeiculosTransporteColetivoAbordados(Integer numVeiculosTransporteColetivoAbordados) {
        this.numVeiculosTransporteColetivoAbordados = numVeiculosTransporteColetivoAbordados;
    }

    public Integer getNumPontosOnibusAbordados() {
        return numPontosOnibusAbordados;
    }

    public void setNumPontosOnibusAbordados(Integer numPontosOnibusAbordados) {
        this.numPontosOnibusAbordados = numPontosOnibusAbordados;
    }

    public Integer getNumTaxiAbordados() {
        return numTaxiAbordados;
    }

    public void setNumTaxiAbordados(Integer numTaxiAbordados) {
        this.numTaxiAbordados = numTaxiAbordados;
    }

    public Integer getNumOcorrenciasEnvolvendoPM() {
        return numOcorrenciasEnvolvendoPM;
    }

    public void setNumOcorrenciasEnvolvendoPM(Integer numOcorrenciasEnvolvendoPM) {
        this.numOcorrenciasEnvolvendoPM = numOcorrenciasEnvolvendoPM;
    }

    public Integer getNumOcorrenciasEnvolvendoPCivil() {
        return numOcorrenciasEnvolvendoPCivil;
    }

    public void setNumOcorrenciasEnvolvendoPCivil(Integer numOcorrenciasEnvolvendoPCivil) {
        this.numOcorrenciasEnvolvendoPCivil = numOcorrenciasEnvolvendoPCivil;
    }

    public Integer getNumOcorrenciasEnvolvendoForcasArmadas() {
        return numOcorrenciasEnvolvendoForcasArmadas;
    }

    public void setNumOcorrenciasEnvolvendoForcasArmadas(Integer numOcorrenciasEnvolvendoForcasArmadas) {
        this.numOcorrenciasEnvolvendoForcasArmadas = numOcorrenciasEnvolvendoForcasArmadas;
    }

    public Integer getNumPessoasConduzidasCP() {
        return numPessoasConduzidasCP;
    }

    public void setNumPessoasConduzidasCP(Integer numPessoasConduzidasCP) {
        this.numPessoasConduzidasCP = numPessoasConduzidasCP;
    }

    public Integer getNumPresosFlagranteDelito() {
        return numPresosFlagranteDelito;
    }

    public void setNumPresosFlagranteDelito(Integer numPresosFlagranteDelito) {
        this.numPresosFlagranteDelito = numPresosFlagranteDelito;
    }

    public Integer getNumAdolescentesApreendidos() {
        return numAdolescentesApreendidos;
    }

    public void setNumAdolescentesApreendidos(Integer numAdolescentesApreendidos) {
        this.numAdolescentesApreendidos = numAdolescentesApreendidos;
    }

    public Integer getNumTCOLavrados() {
        return numTCOLavrados;
    }

    public void setNumTCOLavrados(Integer numTCOLavrados) {
        this.numTCOLavrados = numTCOLavrados;
    }

    public Integer getNumAutoResistenciaLavrado() {
        return numAutoResistenciaLavrado;
    }

    public void setNumAutoResistenciaLavrado(Integer numAutoResistenciaLavrado) {
        this.numAutoResistenciaLavrado = numAutoResistenciaLavrado;
    }

    public Integer getNumMandadosPrisaoCumpridos() {
        return numMandadosPrisaoCumpridos;
    }

    public void setNumMandadosPrisaoCumpridos(Integer numMandadosPrisaoCumpridos) {
        this.numMandadosPrisaoCumpridos = numMandadosPrisaoCumpridos;
    }

    public Integer getNumArmasFogoApreendidas() {
        return numArmasFogoApreendidas;
    }

    public void setNumArmasFogoApreendidas(Integer numArmasFogoApreendidas) {
        this.numArmasFogoApreendidas = numArmasFogoApreendidas;
    }

    public Integer getNumArmasBrancasApreendidas() {
        return numArmasBrancasApreendidas;
    }

    public void setNumArmasBrancasApreendidas(Integer numArmasBrancasApreendidas) {
        this.numArmasBrancasApreendidas = numArmasBrancasApreendidas;
    }

    public Integer getNumVeiculosRecuperados() {
        return numVeiculosRecuperados;
    }

    public void setNumVeiculosRecuperados(Integer numVeiculosRecuperados) {
        this.numVeiculosRecuperados = numVeiculosRecuperados;
    }

    public Integer getNumVeiculos4RodasAutuados() {
        return numVeiculos4RodasAutuados;
    }

    public void setNumVeiculos4RodasAutuados(Integer numVeiculos4RodasAutuados) {
        this.numVeiculos4RodasAutuados = numVeiculos4RodasAutuados;
    }

    public Integer getNumVeiculos2RodasAutuados() {
        return numVeiculos2RodasAutuados;
    }

    public void setNumVeiculos2RodasAutuados(Integer numVeiculos2RodasAutuados) {
        this.numVeiculos2RodasAutuados = numVeiculos2RodasAutuados;
    }

    public Integer getNumVeiculos4RodasApreendidos() {
        return numVeiculos4RodasApreendidos;
    }

    public void setNumVeiculos4RodasApreendidos(Integer numVeiculos4RodasApreendidos) {
        this.numVeiculos4RodasApreendidos = numVeiculos4RodasApreendidos;
    }

    public Integer getNumVeiculos2RodasApreendidos() {
        return numVeiculos2RodasApreendidos;
    }

    public void setNumVeiculos2RodasApreendidos(Integer numVeiculos2RodasApreendidos) {
        this.numVeiculos2RodasApreendidos = numVeiculos2RodasApreendidos;
    }

    public Integer getNumVeiculosTransporteColetivoApreendidos() {
        return numVeiculosTransporteColetivoApreendidos;
    }

    public void setNumVeiculosTransporteColetivoApreendidos(Integer numVeiculosTransporteColetivoApreendidos) {
        this.numVeiculosTransporteColetivoApreendidos = numVeiculosTransporteColetivoApreendidos;
    }

    public Integer getNumCNHApreendidas() {
        return numCNHApreendidas;
    }

    public void setNumCNHApreendidas(Integer numCNHApreendidas) {
        this.numCNHApreendidas = numCNHApreendidas;
    }

    public Integer getNumMaconhaApreendidaemKg() {
        return numMaconhaApreendidaemKg;
    }

    public void setNumMaconhaApreendidaemKg(Integer numMaconhaApreendidaemKg) {
        this.numMaconhaApreendidaemKg = numMaconhaApreendidaemKg;
    }

    public Integer getNumCocainaApreendidaemGramas() {
        return numCocainaApreendidaemGramas;
    }

    public void setNumCocainaApreendidaemGramas(Integer numCocainaApreendidaemGramas) {
        this.numCocainaApreendidaemGramas = numCocainaApreendidaemGramas;
    }

    public Integer getNumCrackApreendidaemGramas() {
        return numCrackApreendidaemGramas;
    }

    public void setNumCrackApreendidaemGramas(Integer numCrackApreendidaemGramas) {
        this.numCrackApreendidaemGramas = numCrackApreendidaemGramas;
    }

    public Integer getNumOutrasdrogasapreendidas() {
        return numOutrasdrogasapreendidas;
    }

    public void setNumOutrasdrogasapreendidas(Integer numOutrasdrogasapreendidas) {
        this.numOutrasdrogasapreendidas = numOutrasdrogasapreendidas;
    }

    @Override
    public String toString() {
        return "Descriçao= " + descricao +
                ", Data= " + dataProdutividade;
    }
}
