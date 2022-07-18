package nix.summer.practice.mvc

import java.awt.FlowLayout
import java.awt.GridLayout
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import kotlin.system.exitProcess

class SwingView(override var controller: Controller) : JFrame(), View {

    private lateinit var mainFrame: JFrame
    private lateinit var controlPanel: JPanel
    private lateinit var infoPanel: JPanel
    private lateinit var orderPanel: JPanel
    private lateinit var fillPanel: JPanel
    private lateinit var infoLabel: JLabel
    private lateinit var waterField: JTextField
    private lateinit var milkField: JTextField
    private lateinit var coffeeField: JTextField
    private lateinit var cupField: JTextField

    init {
        createUI()
    }

    private fun createUI() {
        title = SwingView::class.java.toString()
        infoPanel = JPanel().apply { layout = FlowLayout() }
        controlPanel = JPanel().apply { layout = GridLayout(4, 1) }
        orderPanel = JPanel().apply { layout = GridLayout(1, 4) }
        fillPanel = JPanel().apply { layout = FlowLayout() }

        mainFrame = JFrame("Swing View").apply {
            setSize(700, 700)
            layout = GridLayout(4, 1)
            addWindowListener(object : WindowAdapter() {
                override fun windowClosing(e: WindowEvent?) {
                    exitProcess(0)
                }
            })
            add(infoPanel)
            add(controlPanel)
            add(orderPanel)
            add(fillPanel)
            isVisible = true
        }

        createInfoPanel()
        createControlPanel()
        createFillPanel()

        orderPanel.isVisible = false
        mainFrame.isVisible = true
    }

    private fun createInfoPanel() {
        infoLabel = JLabel("-----------------")
        infoPanel.add(infoLabel)

    }

    private fun createControlPanel() {
        val makeButton = JButton("Make coffee").apply {
            actionCommand = "buy"
            addActionListener {
                controller.takeCommand(actionCommand)
            }
        }
        controlPanel.add(makeButton)

        val takeButton = JButton("Take money").apply {
            actionCommand = "take"
            addActionListener {
                controller.takeCommand(actionCommand)
            }
        }
        controlPanel.add(takeButton)

        val infoButton = JButton("Remaining").apply {
            actionCommand = "remaining"
            addActionListener {
                controller.takeCommand(actionCommand)
            }
        }
        controlPanel.add(infoButton)

        val exitButton = JButton("Exit").apply {
            actionCommand = "exit"
            addActionListener {
                exit()
            }
        }
        controlPanel.add(exitButton)
    }

    private fun createFillPanel() {
        val waterLabel = JLabel("Water:", JLabel.LEFT)
        waterField = JTextField("0", 6)
        fillPanel.add(waterLabel)
        fillPanel.add(waterField)

        val milkLabel = JLabel("Milk:", JLabel.LEFT)
        milkField = JTextField("0", 6)
        fillPanel.add(milkLabel)
        fillPanel.add(milkField)

        val coffeeLabel = JLabel("Coffee beans:", JLabel.LEFT)
        coffeeField = JTextField("0", 6)
        fillPanel.add(coffeeLabel)
        fillPanel.add(coffeeField)

        val cupLabel = JLabel("Cups:", JLabel.LEFT)
        cupField = JTextField("0", 6)
        fillPanel.add(cupLabel)
        fillPanel.add(cupField)

        val fillButton = JButton("Fill").apply {
            actionCommand = "fill"
            addActionListener {
                controller.takeCommand(actionCommand)
            }
        }
        fillPanel.add(fillButton)
    }

    override fun showMessage(info: String): String {
        infoLabel.text = info
        return "Displayed"
    }

    override fun start() {
        showMessage("Machine is started!")
    }

    override fun buyCoffee() {
        orderPanel.isVisible = true
        controlPanel.isVisible = false
        fillPanel.isVisible = false
        val espressoButton = JButton("ESPRESSO").apply {
            actionCommand = "ESPRESSO"
            addActionListener {
                controller.makeCoffee(Coffee.valueOf(actionCommand))
            }
        }

        val latteButton = JButton("LATTE").apply {
            actionCommand = "LATTE"
            addActionListener {
                controller.makeCoffee(Coffee.valueOf(actionCommand))
            }
        }

        val cappuccinoButton = JButton("CAPPUCCINO").apply {
            actionCommand = "CAPPUCCINO"
            addActionListener {
                controller.makeCoffee(Coffee.valueOf(actionCommand))
            }
        }

        val backButton = JButton("Back").apply {
            addActionListener {
                controlPanel.isVisible = true
                fillPanel.isVisible = true
                orderPanel.isVisible = false
                orderPanel.remove(espressoButton)
                orderPanel.remove(latteButton)
                orderPanel.remove(cappuccinoButton)
                orderPanel.remove(this)
            }
        }

        orderPanel.add(espressoButton)
        orderPanel.add(latteButton)
        orderPanel.add(cappuccinoButton)
        orderPanel.add(backButton)
    }

    override fun getIngredients(): Ingredients {
        return Ingredients(
            water = waterField.text.toInt(),
            milk = milkField.text.toInt(),
            coffee = coffeeField.text.toInt(),
            disposableCups = cupField.text.toInt()
        )
    }

    override fun exit() {
        exitProcess(0)
    }
}