package com.example.stage.service

import com.example.stage.dao.InventaireDAO
import com.example.stage.model.entity.Inventaire
import jakarta.annotation.PostConstruct
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Cell
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileInputStream
import java.util.*

@Service
class InventaireService(
    private val inventaireDAO: InventaireDAO
) {

    @PostConstruct
    fun init() {
        // Spécifie le chemin du fichier Excel à importer
        val filePath = "C:\\Users\\Lenovo\\Desktop\\stage\\ETAT_InventaireValorise.xls"  // Chemin vers ton fichier Excel

        // Appel de la méthode d'importation dès le démarrage de l'application
        importFromExcel(filePath)
    }

    fun importFromExcel(filePath: String) {
        val file = File(filePath)
        val fis = FileInputStream(file)

        try {
            // Créer un workbook à partir du fichier Excel
            val workbook = WorkbookFactory.create(fis)
            val sheet: Sheet = workbook.getSheetAt(0) // Prend la première feuille du fichier

            // Parcours de toutes les lignes du fichier Excel
            for (rowIndex in 1 until sheet.physicalNumberOfRows) {
                val row: Row = sheet.getRow(rowIndex) ?: continue // Ignorer les lignes vides

                // Vérifier que la cellule idDart est bien numérique
                val idDartCell = row.getCell(0)
                if (idDartCell == null || idDartCell.cellType != org.apache.poi.ss.usermodel.CellType.NUMERIC) {
                    continue
                }

                val idDart = idDartCell.numericCellValue.toInt()
                val designation = row.getCell(1).getStringCellValue()
                val stock = if (row.getCell(3).cellType == org.apache.poi.ss.usermodel.CellType.NUMERIC) {
                    row.getCell(3).numericCellValue.toInt()
                } else {
                    null
                }
                val puHt = if (row.getCell(4).cellType == org.apache.poi.ss.usermodel.CellType.NUMERIC) {
                    row.getCell(4).numericCellValue
                } else {
                    null
                }

                // Création de l'objet Inventaire et insertion dans la base de données
                val inventaire = Inventaire(
                    idDart = idDart,
                    designation = designation,
                    stock = stock,
                    puHt = puHt
                )

                inventaireDAO.save(inventaire)
            }
        } catch (e: Exception) {
            throw RuntimeException("Erreur lors du traitement du fichier Excel", e)
        } finally {
            fis.close()
        }
    }
}