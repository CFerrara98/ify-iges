INSERT INTO `ify`.`utente` (`id`, `cognome`, `email`, `indirizzo`, `nome`, `password`, `sesso`) VALUES ('1', 'Rossi', 'ufficiotirocini@unisa.it', 'Via Roma 4 84080 Salerno SA', 'Mario', 'admin', 'M');
INSERT INTO `ify`.`responsabile_ufficio_tirocini` (`ruolo`, `id`) VALUES ('responsabile', '1');
INSERT INTO `ify`.`utente` (`id`, `cognome`, `email`, `indirizzo`, `nome`, `password`, `sesso`) VALUES ('2', 'Verdi', 'l.verdi2@studenti.unisa.it', 'Via Milano 4 84080 Salerno SA', 'Luigi', 'studente123', 'M');
INSERT INTO `ify`.`studente` (`data_nascita`, `matricola`, `telefono`, `id`) VALUES ('1998-10-15', '0512105569', '3312006189', '2');
INSERT INTO `ify`.`richiesta_iscrizione` (`id`, `stato`, `studente_id`) VALUES ('1', 'in attesa', '2');
INSERT INTO `ify`.`azienda` (`piva`, `descrizione`, `ragione_sociale`, `sede`, `settore`) VALUES ('1234567812', 'azienda per il testing', 'azienda testing', 'Salerno', 'Informatica');
INSERT INTO `ify`.`utente` (`id`, `cognome`, `email`, `indirizzo`, `nome`, `password`, `sesso`) VALUES ('3', 'Bianchi', 'b.bianchi@gmail.com', 'Via Napoli 4 84080 Salerno SA', 'Luca', 'azienda', 'M');
INSERT INTO `ify`.`delegato_aziendale` (`ruolo`, `id`, `azienda_piva`) VALUES ('Direttore', '3', '1234567812');
INSERT INTO `ify`.`richiesta_convenzionamento` (`id`, `stato`, `azienda_piva`, `delegato_aziendale_id`) VALUES ('2', 'accettata', '1234567812', '3');
INSERT INTO `ify`.`progetto_formativo` (`id`, `ambito`, `attivita`, `conoscenze`, `data_compilazione`, `descrizione`, `max_partecipanti`, `nome`, `stato`, `azienda_piva`) VALUES ('1', 'Informatica', 'Progetto', 'C e Java', '2020-01-11', 'progetto testing', '2', 'Prog Testing', 'attivo', '1234567812');
INSERT INTO `ify`.`utente` (`id`, `cognome`, `email`, `indirizzo`, `nome`, `password`, `sesso`) VALUES ('4', 'De Lucia', 'adelucia@unisa.it', 'Via Napoli 4 84080 Salerno SA', 'Andrea', 'adelucia', 'M');
INSERT INTO `ify`.`docente_tutor` (`id`, `campo_ricerca`) VALUES ('4', 'Ingegneria del Software');
INSERT INTO `ify`.`domanda_tirocinio` (`id`, `cfu`, `conoscenze`, `data_fine`, `data_inizio`, `motivazioni`, `stato`, `azienda_piva`, `docente_tutor_id`, `progetto_formativo_id`, `studente_id`) 
	VALUES ('1', '6', 'C e Java ', '2020-02-20', '2020-01-13', 'Imparare nuovi linguaggi', 'in attesa', '1234567812', '4', '1', '2');
INSERT INTO `ify`.`utente` (`id`, `cognome`, `email`, `indirizzo`, `nome`, `password`, `sesso`) VALUES ('5', 'Giallo', 'l.giallo@studenti.unisa.it', 'Via Milano 4 84080 Salerno SA', 'Luigi', 'studente123', 'M');
INSERT INTO `ify`.`studente` (`data_nascita`, `matricola`, `telefono`, `id`) VALUES ('1998-10-15', '0512105555', '3312006189', '5');
INSERT INTO `ify`.`richiesta_iscrizione` (`id`, `stato`, `studente_id`) VALUES ('2', 'in attesa', '5');
INSERT INTO `ify`.`domanda_tirocinio` (`id`, `cfu`, `conoscenze`, `data_fine`, `data_inizio`, `motivazioni`, `stato`, `azienda_piva`, `docente_tutor_id`, `progetto_formativo_id`, `studente_id`) 
	VALUES ('2', '6', 'C e Java ', '2020-02-20', '2020-01-13', 'Imparare nuovi linguaggi', 'accettata_tutor', '1234567812', '4', '1', '5');