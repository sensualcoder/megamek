/*
 * Copyright (c) 2024 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMek.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */

package megamek.client.ui.swing;

import megamek.client.ui.Messages;
import megamek.client.ui.swing.util.UIUtil;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.util.List;

import static megamek.client.ui.swing.util.UIUtil.spanCSS;

public class DeployElevationChoiceDialog extends AbstractChoiceDialog<DeploymentDisplay.ElevationOption> {

    private static final int BASE_PADDING = 10;
    private static final int BASE_JUMP_SIZE = 35;

    protected DeployElevationChoiceDialog(JFrame parent, List<DeploymentDisplay.ElevationOption> elevationOptions) {
        super(parent, "DeploymentDisplay.choiceDialogTitle", titleMessage(), elevationOptions, false);
        setColumns(elevationOptions.size() > 6 ? 2 : 1);
        initialize();
        setUseDetailed(false);
    }

    @Override
    protected void detailLabel(JToggleButton button, DeploymentDisplay.ElevationOption elevationOption) {
        //Messages.getString("DeploymentDisplay.ground") +
        String targetText = elevationOption.type() + "";
        targetText += "<BR>Elevation: " + elevationOption.elevation();
        String text = "<HTML><HEAD>" + styles() + "</HEAD><BODY>"
                + spanCSS("button", targetText)
                + "</BODY></HTML>";
        button.setText(text);
    }

    @Override
    protected JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(1, 0, 0, 0, UIManager.getColor("Separator.foreground")),
                new UIUtil.ScaledEmptyBorder(10, 0, 10, 0)));
        buttonPanel.add(new ButtonEsc(new CloseAction(this)));
        return buttonPanel;
    }

    @Override
    protected void summaryLabel(JToggleButton button, DeploymentDisplay.ElevationOption target) {
        detailLabel(button, target);
    }

    private static String titleMessage() {
        return "<HTML><HEAD>" + styles() + "</HEAD><BODY><div class=frame>"
                + spanCSS("label", "Choose the")
                + spanCSS("speccell", " JUMP ")
                + spanCSS("label", "points to use:")
                + "</div></BODY></HTML>";
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            UIUtil.adjustDialog(this, UIUtil.FONT_SCALE1);
            pack();
        }
        super.setVisible(visible);
    }

    public static String styles() {
        float labelSize = UIUtil.scaleForGUI(UIUtil.FONT_SCALE1);
        int padding = UIUtil.scaleForGUI(BASE_PADDING);
        int buttonSize = UIUtil.scaleForGUI(BASE_JUMP_SIZE);
        return "<style> " +
                ".label { font-family:Noto Sans; font-size:" + labelSize + ";  }" +
                ".frame { padding:" + padding + " " + 2 * padding + " 0 0;  }" +
                ".speccell { font-family:Exo; font-size:" + labelSize + "; }" +
                ".button { font-family:Exo; font-size:" + buttonSize + "; }";
    }
}
