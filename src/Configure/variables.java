/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configure;

/**
 *
 * @author Notebook
 */
import io.github.cdimascio.dotenv.Dotenv;

public class variables {

    Dotenv dotenv;

    public variables() {

        dotenv = Dotenv.configure()
                .directory("src/../configure/env")
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();

    }

    public String get(String name) {

        return dotenv.get(name);

    }

}
