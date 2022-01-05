package Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

import org.apache.tika.io.IOUtils;

import javax.swing.JFileChooser;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class FrameWordCounter extends JFrame {

	private JPanel contentPane;
	private JTextField txtFile;
	private String str;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameWordCounter frame = new FrameWordCounter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameWordCounter() {
		setTitle("WordCounter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 689);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFile = new JLabel("Open file(txt)");
		lblFile.setBounds(40, 34, 109, 15);
		contentPane.add(lblFile);

		txtFile = new JTextField();
		txtFile.setBounds(163, 32, 500, 19);
		contentPane.add(txtFile);
		txtFile.setColumns(10);

		JLabel lblXutNiDung = new JLabel("Xuất Nội Dung");
		lblXutNiDung.setBounds(40, 82, 109, 15);
		contentPane.add(lblXutNiDung);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(163, 81, 500, 147);
		contentPane.add(scrollPane);

		JTextArea txtNoiDung = new JTextArea();
		scrollPane.setViewportView(txtNoiDung);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				//Chon 1 file bat ki tren bo nho
				JFileChooser fcs = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				fcs.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				if (fcs.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File fileSelected = fcs.getSelectedFile();
					txtFile.setText(fileSelected.getAbsolutePath());

					try (DataInputStream in = new DataInputStream(
							new BufferedInputStream(new FileInputStream(fileSelected.getAbsolutePath())))) {

						str = IOUtils.toString(in);
						in.close();
					} catch (EOFException e) {

					} catch (IOException ex) {
						Logger.getLogger(FrameWordCounter.class.getName()).log(java.util.logging.Level.SEVERE, null,
								ex);
					} catch (NullPointerException e) {

					}
					txtNoiDung.setText(str);
					txtNoiDung.setEditable(false);
				}
				;
			}
		});
		btnBrowse.setBounds(695, 29, 117, 25);
		contentPane.add(btnBrowse);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(163, 269, 500, 371);
		contentPane.add(scrollPane_1);

		JList listKetQua = new JList();
		scrollPane_1.setViewportView(listKetQua);

		JLabel lblXutKtQu = new JLabel("Xuất kết quả");
		lblXutKtQu.setBounds(23, 269, 126, 15);
		contentPane.add(lblXutKtQu);
		// Tao Doi tuong listModel de luu bien va xuat gia tri ra man hinh
		DefaultListModel listModel = new DefaultListModel();
		JButton btnKetQua = new JButton("Kết quả");
		btnKetQua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Khoi tao WordFrequencyAnalyzer va truyen tham so str vao
				WordFrequencyAnalyzer analyzer = new WordFrequencyAnalyzer(str);
				// Nhung analyzer vo WordFrequecyCollection de de xu ly phan dem so
				WordFrequecyCollection collection = new WordFrequecyCollection(analyzer);
				//Xem dinh dang ngon ngu trong File
				listModel.addAll(analyzer.analyzeText());
				//Xuat cac tu duoc dem cung loai
				for (String s : collection.getFequency()) {
					listModel.addElement(s);
				}
//            listModel.addElement(collection.getFequency());
				listKetQua.setModel(listModel);
			}
		});
		btnKetQua.setBounds(342, 240, 117, 25);
		contentPane.add(btnKetQua);

		JButton btnXuatFile = new JButton("Save File");
		btnXuatFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fcs = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				fcs.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				if (fcs.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File fileSelected = fcs.getSelectedFile();
					try (DataOutputStream out = new DataOutputStream(
							new BufferedOutputStream(new FileOutputStream(fileSelected.getAbsolutePath())))) {
						out.writeUTF(listModel.toString());
						//Thong bao File da duoc luu
						JOptionPane.showMessageDialog(rootPane, "File da duoc luu.");
						out.close();
					} catch (FileNotFoundException ex) {
						Logger.getLogger(FrameWordCounter.class.getName()).log(Level.SEVERE, null, ex);
					} catch (IOException ex) {
						Logger.getLogger(FrameWordCounter.class.getName()).log(Level.SEVERE, null, ex);
					}

				}
				;
			}
		});
		btnXuatFile.setBounds(695, 265, 117, 25);
		contentPane.add(btnXuatFile);

	}
}
