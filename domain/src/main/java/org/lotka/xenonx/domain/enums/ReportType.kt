package org.lotka.xenonx.domain.enums

import androidx.annotation.StringRes
import org.lotka.xenonx.domain.R


sealed class ReportType(val index: Int, @StringRes val resourceId: Int) {
    object AgencyReport : ReportType(index = 0, resourceId = R.string.agency_report)

    object VisitsAndClicks : ReportType(index = 1, resourceId = R.string.view_and_click_report)
    object SalesAndPreSales : ReportType(index = 2, resourceId = R.string.sale_and_presale_report)
    object MortgageAndRent : ReportType(index = 3, resourceId = R.string.mortgage_and_rent_report)
    object AssignedLeads : ReportType(index = 4, resourceId = R.string.assigned_leads_report)
}