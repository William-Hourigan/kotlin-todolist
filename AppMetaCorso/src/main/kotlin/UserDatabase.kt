//  Classe del database degli utenti e tutti i metodi collegati
class UserDatabase {
    //  Array di oggetti Task
    var userDatabase: ArrayList<User> = ArrayList()

    //  Contatore per gli ID (dato che gli ID devono essere univoci)
    var contatoreID: Int = 1

    /*
     CREATE
     */

    //  Funzione che crea un nuovo utente
    fun createUser(username: String, email: String, password: String, secretQuestion: String, secretAnswer: String) {
        //  Tutti i dati tranne l'ID sono passati al richiamo della funzione
        userDatabase.add(User(contatoreID, username, email, password, secretQuestion, secretAnswer))
        //  Stampo che la registrazione dell'utente è stata completata e incremento di 1 il contatore degli ID
        println("\n\tRegistrazione completata.")
        contatoreID += 1
    }


    /*
     READ
     */

    //  Funzione che stampa il database se non è vuoto
    fun readUser() {
        //  Se il database non è vuoto
        if(userDatabase.isNotEmpty()) {
            println()
            //  Cicla ogni elemento del database degli utenti
            for (i in 0 until userDatabase.size) {
                //  Stampa l'ID dell'utente, lo username, e l'email
                println("${userDatabase[i].id}) Username: ${userDatabase[i].username} - Email: ${userDatabase[i].email}")
            }
        }
        //  Se il database è vuoto
        else {
            //  Stampa che non ci sono utenti
            println("\n\tNessun utente registrato.")
        }
    }

    /*
     UPDATE
     */


    //  Funzione che cambia la password a un utente che ha come username/email il valore passato come parametro
    fun cambiaPassword(usernameOEmailUtente: String) {
        //  Variabile usata per contenere la posizione dell'utente con username/email richiesto
        var num = 0
        //  Variabile per leggere l'input dell'utente
        var leggiString: String = ""

        //  Ciclo il database degli utenti
        for (i in 0 until userDatabase.size) {
            //  Se la stringa passato come parametro è uguale all'username o l'email dell'utente nella posizione i del database
            if (usernameOEmailUtente==userDatabase[i].username || usernameOEmailUtente==userDatabase[i].email) {
                //  Num prende il valore di i e tramite il break usciamo dal ciclo for immediatamente
                num = i
                break
            }
        }
        println("\nRispondi alla seguente domanda per poter cambiare la password")
        //  Stampo la domanda segreta dell'utente e salvo la risposta in leggiString
        println("${userDatabase[num].secretQuestion}")
        leggiString = readLine().toString()
        //  Se la risposta data dall'utente è uguale alla risposta segreta
        if (leggiString.equals(userDatabase[num].secretAnswer)) {
            //  Chiedo all'utente di mettere una nuova password e la salvo in leggiString
            println("\nImmetti nuova password.")
            leggiString = readLine().toString()
            //  Sovrascrivo la password vecchia con la risposta dell'utente
            userDatabase[num].password = leggiString
            println("\n\tPassword salvata.")
            //mettere i controlli sulla password
        }
        //  Se la risposta data dall'utente NON è uguale alla risposta segreta
        else {
            //  Stampo che la risposta è sbagliata e esco dal menu del cambiamento della password senza modificare
            println("\nRisposta sbagliata.")
        }
    }

    /*
     DELETE
     */

    //  Funziona che elimina un utente che ha come username il valore passato come parametro
    fun deleteUser(username: String){
        //  Variabile di controllo per vedere se esiste l'utente con username uguale allo username passato come parametro
        var controllo: Boolean = false
        //  Variabile usata per contenere la posizione dell'utente con username specificato
        var num = 0
        //  Ciclo tutto il database degli utenti
        for (i in 0 until userDatabase.size) {
            //  Se l'utente in posizione i ha username uguale allo username passato come parametro
            if (userDatabase[i].username==username) {
                //  La variabile di controllo viene messa a true, num prende il valore di i, e usciamo dal ciclo for immediatamente tramite break
                controllo = true
                num = i
                break
            }
        }

        //  Se l'utente con username specificato esiste
        if (controllo == true) {
            //  Elimino l'utente in posizione num e stampo di aver eliminato l'utente
            userDatabase.removeAt(num)
            println("\n\tUtente eliminato.")
        }
        //  Se l'utente con username specificato esiste
        else {
            //  Stampo che non esiste un utente che ha come username il valore passato come parametro
            println("\n\tUtente con USERNAME: ${username} non esistente.")
        }
    }


    /*
    FUNZIONE DI CONTROLLO
     */

    //  Funziona che controlla se un username è gia in utilizzo
    fun checkUsernameAvailability(username: String): Boolean {
        //  Variabile di controllo per vedere se lo username è disponibile
        var controllo: Boolean = true
        //  Ciclo il database utenti
        for (i in 0 until userDatabase.size) {
            //  Se lo username dell'utente nella posizione i è uguale a lo username passato come parametro
            if (userDatabase[i].username==username) {
                //  La variabile di controllo viene posto a false e usciamo dal ciclo for immediatamente tramite break
                controllo = false
                break
            }
        }
        //  Si fa il return del controllo (se lo username è disponibile ritorna true, altrimenti ritorna false)
        return controllo
    }

    //  Funziona che controlla se un email è gia in utilizzo
    fun checkEmailAvailability(email: String): Boolean {
        //  Variabile di controllo per vedere se l'email è disponibile
        var controllo: Boolean = true
        //  Ciclo il database utenti
        for (i in 0 until userDatabase.size) {
            //  Se l'email dell'utente nella posizione i è uguale a l'email passato come parametro
            if (userDatabase[i].email==email) {
                //  La variabile di controllo viene posto a false e usciamo dal ciclo for immediatamente tramite break
                controllo = false
                break
            }
        }
        //  Si fa il return del controllo (se lo username è disponibile ritorna true, altrimenti ritorna false)
        return controllo
    }

    //  Funzione che effettua il login e ritorna l'ID dell'utente che ha fatto il login (o null se il login fallisce)
    fun login(usernameOemail: String, password: String): Int? {
        //  Variabili di controllo per vedere se username/email e password vanno bene
        var controllo1: Boolean = false
        var controllo2: Boolean = false
        //  Variabile usata per contenere la posizione dell'utente con username/email specificato
        var num: Int = 0

        //  Ciclo il database utenti
        for (i in 0 until userDatabase.size) {
            //  Se la stringa usernameOemail passato come parametro è uguale all'username o l'email dell'utente nella posizione i del database
            if (usernameOemail==userDatabase[i].username || usernameOemail==userDatabase[i].email) {
                //  La prima variabile di controllo viene messa a true, num prende il valore di i, e usciamo dal ciclo for immediatamente tramite break
                num = i
                controllo1 = true
                break
            }
        }
        //  Se non esiste l'utente con username o email uguale a usernameOemail passato come parametro
        if (!controllo1) {
            //  Ritorno null (login fallito)
            return null
        }
        //  Se la password passata come parametro è uguale alla password dell'utente che ha nome o email uguale a usernameOemail
        if (password == userDatabase[num].password) {
            controllo2 = true
        }
        //  Se il controllo sulla password è andata a buon fine
        if (controllo2) {
            //  Ritorno l'id dell'utente nella posizione num (login esegiuto con successo)
            return userDatabase[num].id
        }
        //  Se il controllo sulla password NON è andata a buon fine
        else {
            //  Ritorno null (login fallito)
            return null
        }
    }


    //  Funzione che ritorna true se il database degli utenti è vuoto, altrimenti stampa false
    fun checkUserDatabaseEmpty(): Boolean {
        if(userDatabase.isEmpty()) {
            return true
        }
        else {
            return false
        }
    }

}