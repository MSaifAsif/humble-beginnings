/*
 * Created by SharpDevelop.
 * User: Muhammad Saif
 * Date: 11/22/2010
 * Time: 9:22 PM
 * 
 * To change this template use Tools | Options | Coding | Edit Standard Headers.
 */
using System;
using System.Management;
using System.IO;
using System.Drawing;
using System.Windows.Forms;
using System.Text;

namespace InfoID
{
	class Program
	{
		// -----------------> services
		static void get_service()
		{
				string[] s = new string[200] ;
			 ManagementClass c = new ManagementClass("Win32_Service");
	int i=0;
        foreach (ManagementObject o in c.GetInstances())
        {
        	s[i] =  ("\r\n"+i+". "+" Service Name = " +o["Name"] + "\t\r\n ProcessId = "+o["ProcessId"]+ "\t\r\n Instance Path ="+ o.Path +"\t\r\n status = " + o["State"]);
        	
        	write_f(s[i]+"\r\n");
        	i++;
        }
		}
		
		
		// ----------------> BIOS
		static void get_bios()
		{
		ManagementClass d = new ManagementClass("Win32_BIOS");
        int i=0; string[] s = new string[10];
        foreach (ManagementObject o in d.GetInstances())
        {
        	s[i] =  ("\r\n Name = " +o["Name"] + "\n\r\n Version = "+o["BIOSVersion"]+ "\n\r\n Version = "+o["Version"]+"\n\r\n Caption ="+o["Caption"]+"\n\r\n Language = "+o["CurrentLanguage"] + "\n\r\n primary? ="+o["PrimaryBios"]+ "\n\r\n Serial Number = "+ o["serialnumber"].ToString() +  "\n\r\n smbios = "+o["SMBIOSBIOSVersion"] +"\n\r\n minor = "  +o["SMBIOSMajorVersion"] + "\n\r\n major = " + o["SMBIOSMinorVersion"] + "\n\r\n SoftID ="+o["SoftwareElementID"] + "\n\r\n Soft State =" + o["SoftwareElementState"]+ "\n\r\n status =" +o["Status"] +"\n\r\n Code Set =" +o["CodeSet"] + "\n\r\n Description = "+o["Description"]) ;
			write_f(s[i]+"\r\n");
			i++;
        }
		}
		
		// --------------------> Disk Drive
		
		static void get_DiskDrive()
		{
		ManagementClass e = new ManagementClass("Win32_DiskDrive");
		int i=0; string s2;
        
        foreach (ManagementObject o in e.GetInstances())
        {
        	
        	
        	Console.WriteLine("in");
        	s2 = ("Name = "+o["Name"] + "\r\n\nmodel = " + o["model"] + "\n\r\n TotalHeads = " + o["TotalHeads"] + "\n\r\n TotalSectors = " + o["TotalSectors"] + "\n\r\n TotalTracks = " + o["TotalTracks"] + "\n\r\n TotalCylinders = " + o["TotalCylinders"] + "\n\r\n SectorsPerTrack = " + o["SectorsPerTrack"] + "\n\r\n SerialNumber = " + o["SerialNumber"] +"\n\r\n Signature = "+o["Signature"] + "\n\r\n Size = " +o["Size"] + "\n\r\n Partitions = " + o["Partitions"]);
        	write_f(s2+"\r\n");
        	i++;
        }
		}
		
		static void get_bus()
		{
			 ManagementClass f = new ManagementClass("Win32_Bus");
			 int i=0; string s3;
        
        foreach (ManagementObject o in f.GetInstances())
        {
        	
        	
        	
        	s3 = ("\r\n Name = "+o["Name"] +"\n\r\n Availability = "+o["Availability"] + "\n\r\n BusNum = " + o["BusNum"] + "\n\r\n BusType = " + o["BusType"] + "\n\r\n State = " + o["StatusInfo"]);
        	Console.WriteLine(s3);
        	write_f(s3+"\r\n");
        	i++;
        }

		}
		
		
		//----------------------> Base Board
		
		static void get_baseboard()
		{
			ManagementClass g = new ManagementClass("Win32_BaseBoard");
			int i=0; string s4;
        
        foreach (ManagementObject o in g.GetInstances())
        {
        	
        	
        	
        	s4 = ("\r\n Caption" + o["Caption"] + "\n\r\n CreationClassName" + o["CreationClassName"] + "\n\r\n Depth" + o["Depth"] + "\n\r\n Description" + o["Description"] + "\n\r\n Height" + o["Height"] + "\n\r\n HostingBoard ? " + o["HostingBoard"] + "\n\r\n HotSwappable ?" + o["HotSwappable"] + "\n\r\n InstallDate" + o["InstallDate"] + "\n\r\n Manufacturer" + o["Manufacturer"] + "\n\r\n Model" + o["Model"] + "\n\r\n Name" + o["Name"] + "\n\r\n OtherIdentifyingInfo" + o["OtherIdentifyingInfo"] + "\n\r\n PartNumber" + o["PartNumber"] + "\n\r\n PoweredOn ? " + o["PoweredOn"] + "\n\r\n Product" + o["Product"] + "\n\r\n Removable ? " + o["Removable"] + "\n\r\n Replaceable ?" + o["Replaceable"] + "\n\r\n RequirementsDescription" + o["RequirementsDescription"] + "\n\r\n RequiresDaughterBoard ? " + o["RequiresDaughterBoard"] + "\n\r\n SerialNumber" + o["SerialNumber"] + "\n\r\n SKU" + o["SKU"] + "\n\r\n SlotLayout" + o["SlotLayout"] + "\n\r\n SpecialRequirements ? " + o["SpecialRequirements"] + "\n\r\n Status" + o["Status"] + "\n\r\n Tag" + o["Tag"] + "\n\r\n Version" + o["Version"] + "\n\r\n Weight"  + o["Weight"] + "\n\r\n Width" + o["Width"]);
        	
        	i++;
        	write_f(s4+"\r\n");
        }

		}
		
		//---------------------> File Stream
		
		static void write_f(string str)
		{
			
			Console.WriteLine(str);
			//Console.ReadLine();
       	FileStream fs = new FileStream("test1.txt", FileMode.Append);
        StreamWriter tw = new StreamWriter(fs);

               	
                 tw.WriteLine(str+"\r\n");
                tw.Close();

			
		}
		
		
		
		// ---------------------> Main
		
		public static void Main(string[] args)
		{
			
			System.Windows.Forms.MessageBox.Show("Starting Service");
			get_service();
			get_bios();		
			get_DiskDrive();
			get_bus();
			
			
			System.Windows.Forms.MessageBox.Show("DONE");
			//Console.Write("Press any key to continue . . . ");
			//Console.ReadKey(true);
		}
	}
}