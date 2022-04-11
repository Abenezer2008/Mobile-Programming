package edu.miu.cvBuilder.ui.dialog

import edu.miu.cvBuilder.domain.Work

interface WorkDialogCommunicator {
    fun onAddWOrk(work: Work)
}